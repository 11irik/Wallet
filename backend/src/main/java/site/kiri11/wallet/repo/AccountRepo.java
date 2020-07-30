package site.kiri11.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;

import java.util.Set;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Set<Account> findByUsers(User user);
}
