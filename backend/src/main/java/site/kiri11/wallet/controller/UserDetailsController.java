package site.kiri11.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.repo.UserDetailsRepo;
import site.kiri11.wallet.service.UserDetailsService;

@RestController
@RequestMapping("user")
public class UserDetailsController {
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public User setDefaultAccount(@RequestBody Account account, @AuthenticationPrincipal User user) {
        return userDetailsService.setDefaultAccount(user, account);
    }
}
