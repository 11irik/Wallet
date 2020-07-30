package site.kiri11.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.repo.AccountRepo;

@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AccountService(AccountRepo accountRepo, UserDetailsService userDetailsService) {
        this.accountRepo = accountRepo;
        this.userDetailsService = userDetailsService;
    }

    public Account save(Account account, User user) {
        account.setOwner(user);
        account.getUsers().add(user);
        account.setOwner(user);
        return accountRepo.save(account);
    }

    public Account addUser(Long accountId, String userEmail, User user) {
        Account accountDB = accountRepo.getOne(accountId);
        User userDB = userDetailsService.getByEmail(userEmail);
        accountDB.getUsers().add(userDB);
        return accountRepo.save(accountDB);
    }

    public Account findById(Long id) {
        return accountRepo.findById(id).get();
    }
}
