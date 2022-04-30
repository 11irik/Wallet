package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.User;

import java.util.Set;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Set<Account> findByUsers(User user);
}
