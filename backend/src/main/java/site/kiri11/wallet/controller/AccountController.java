package site.kiri11.wallet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.dto.UserInviteDto;
import site.kiri11.wallet.repo.AccountRepo;
import site.kiri11.wallet.repo.UserDetailsRepo;
import site.kiri11.wallet.service.AccountService;
import site.kiri11.wallet.service.UserDetailsService;

@RestController
@RequestMapping("account")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account create(@RequestBody Account account, @AuthenticationPrincipal User user) {
        return accountService.save(account, user);
    }

    @PostMapping("user")
    private Account addUser(@RequestBody UserInviteDto dto, @AuthenticationPrincipal User user) {
        String userEmail = dto.getEmail();
        Long accountId = dto.getAccountId();

        return accountService.addUser(accountId, userEmail, user);
    }

    @GetMapping("{id}")
    public Account get(@PathVariable("id") Account account, @AuthenticationPrincipal User usre) {
        return account;//todo auth
    }
}
