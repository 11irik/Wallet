package site.kiri11.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import site.kiri11.wallet.domain.TransactionTag;

public interface TransactionTagRepo extends JpaRepository<TransactionTag, Long> {
}
