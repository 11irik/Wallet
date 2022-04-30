package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griddodicks.wallet.domain.TransactionTag;

public interface TransactionTagRepo extends JpaRepository<TransactionTag, Long> {
}
