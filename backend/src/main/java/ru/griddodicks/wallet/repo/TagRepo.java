package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.Tag;

import java.util.Set;

public interface TagRepo extends JpaRepository<Tag, Long> {
    Set<Tag> findByAccount(Account account);
}
