package site.kiri11.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.domain.Vault;
import site.kiri11.wallet.repo.VaultRepo;

import javax.naming.InvalidNameException;
import java.util.Set;

@Service
public class VaultService {
    private final VaultRepo vaultRepo;
    private final AccountService accountService;

    @Autowired
    public VaultService(VaultRepo vaultRepo, AccountService accountService) {
        this.vaultRepo = vaultRepo;
        this.accountService = accountService;
    }

    public Vault create(String name, Double sum, Long accountId, User user) {
        Vault vault = new Vault();
        vault.setName(name);
        vault.setAccount(accountService.findById(accountId));
        vault.setSum(sum);

        return vaultRepo.save(vault);
    }

    public Set<Vault> getAccountVaults(Account account, User user) {
        return vaultRepo.findByAccount(account);
    }
}
