package site.kiri11.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Tag;

import java.util.Set;

public interface TagRepo extends JpaRepository<Tag, Long> {
    Set<Tag> findByAccount(Account account);
}
