package ru.griddodicks.wallet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.dto.UserInviteDto;
import ru.griddodicks.wallet.service.AccountService;

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
