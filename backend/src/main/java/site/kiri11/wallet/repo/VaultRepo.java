package site.kiri11.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Vault;

import java.util.Set;

public interface VaultRepo extends JpaRepository<Vault, Long> {
    Set<Vault> findByAccount(Account account);
}
