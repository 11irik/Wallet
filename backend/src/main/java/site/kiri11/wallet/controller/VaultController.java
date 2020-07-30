package site.kiri11.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.domain.Vault;
import site.kiri11.wallet.dto.VaultDto;
import site.kiri11.wallet.service.AccountService;
import site.kiri11.wallet.service.VaultService;

import javax.naming.InvalidNameException;
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
