package ru.griddodicks.wallet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo, AccountRepo accountRepo) {
//        return map -> {
//            String id = (String) map.get("sub");
//
//            User user = userDetailsRepo.findById(id).orElseGet(() -> {
//                User newUser = new User();
//                newUser.setId(id);
//                newUser.setName((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//                User savedUser = userDetailsRepo.save(newUser);
//
//                Account defaultAccount = new Account();
//                defaultAccount.setName("Stash");
//                Account savedAccount = accountRepo.save(defaultAccount);
//                savedAccount.setOwner(savedUser);
//                savedAccount.getUsers().add(savedUser);
//                accountRepo.save(savedAccount);
//
//                savedUser.setDefaultAccount(savedAccount);
//                return userDetailsRepo.save(savedUser);
//            });
//
//            user.setLastVisit(LocalDateTime.now());
//
//            return userDetailsRepo.save(user);
//        };
//    }
}
