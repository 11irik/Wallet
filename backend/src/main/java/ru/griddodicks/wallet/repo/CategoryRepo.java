package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.Category;

import java.util.Set;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Set<Category> findByAccount(Account account);
}
