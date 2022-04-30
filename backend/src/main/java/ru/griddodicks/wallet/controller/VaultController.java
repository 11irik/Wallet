package ru.griddodicks.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.domain.Vault;
import ru.griddodicks.wallet.dto.VaultDto;
import ru.griddodicks.wallet.service.AccountService;
import ru.griddodicks.wallet.service.VaultService;

import java.util.Set;

@RestController
@RequestMapping("vault")
public class VaultController {
    private final VaultService vaultService;
    private final AccountService accountService;

    @Autowired
    public VaultController(VaultService vaultService, AccountService accountService) {
        this.vaultService = vaultService;
        this.accountService = accountService;
    }

    @PostMapping
    public Vault create(@RequestBody VaultDto dto, @AuthenticationPrincipal User user) {
        return vaultService.create(dto.getName(), dto.getSum(), dto.getAccountId(), user);
    }

    @PostMapping("account")
    public Set<Vault> getAccountVaults(@RequestBody Account account, @AuthenticationPrincipal User user) {
        return vaultService.getAccountVaults(account, user);
    }

}
