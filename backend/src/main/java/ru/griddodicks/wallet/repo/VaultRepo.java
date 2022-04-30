package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.Vault;

import java.util.Set;

public interface VaultRepo extends JpaRepository<Vault, Long> {
    Set<Vault> findByAccount(Account account);
}
