package site.kiri11.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Category;

import java.util.Set;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Set<Category> findByAccount(Account account);
}
