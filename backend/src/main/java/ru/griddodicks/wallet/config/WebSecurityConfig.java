package ru.griddodicks.wallet.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.repo.AccountRepo;
import ru.griddodicks.wallet.repo.UserDetailsRepo;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo, AccountRepo accountRepo) {
        create(userDetailsRepo, accountRepo);
        return map -> {
            String id = (String) map.get("sub");

            User user = userDetailsRepo.findById(id).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserpic((String) map.get("picture"));
                User savedUser = userDetailsRepo.save(newUser);

                Account defaultAccount = new Account();
                defaultAccount.setName("Stash");
                Account savedAccount = accountRepo.save(defaultAccount);
                savedAccount.setOwner(savedUser);
                savedAccount.getUsers().add(savedUser);
                accountRepo.save(savedAccount);

                savedUser.setDefaultAccount(savedAccount);
                return userDetailsRepo.save(savedUser);
            });

            user.setLastVisit(LocalDateTime.now());

            return userDetailsRepo.save(user);
        };
    }

    private User create(UserDetailsRepo userDetailsRepo, AccountRepo accountRepo) {
        User newUser = new User();
        newUser.setId("12345");
        newUser.setName("Kirill");
        newUser.setEmail("lykashin.kirill@gmail.com");
        newUser.setGender("male");
        newUser.setLocale("ru");
        newUser.setUserpic("df");
        User savedUser = userDetailsRepo.save(newUser);

        Account defaultAccount = new Account();
        defaultAccount.setName("Stash");
        Account savedAccount = accountRepo.save(defaultAccount);
        savedAccount.setOwner(savedUser);
        savedAccount.getUsers().add(savedUser);
        accountRepo.save(savedAccount);

        savedUser.setDefaultAccount(savedAccount);
        return userDetailsRepo.save(savedUser);
    }
}
