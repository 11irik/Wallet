package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.Transaction;

import java.time.LocalDate;
import java.util.Set;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Set<Transaction> findByVault_Account(Account account);

    Set<Transaction> findByVault_AccountAndDateBetween(Account account, LocalDate start, LocalDate end);

    @Query(value = "select sum(transaction.sum)\n" +
            "from transaction\n" +
            "         inner join vault v on transaction.vault_id = v.id\n" +
            "         inner join account a on v.account_id = a.id\n" +
            "         inner join transaction_tag tt on transaction.transaction_tag_id = tt.id\n" +
            "where date = ?2 and account_id = ?1 and tt.getting = false", nativeQuery = true)
    Double getTransactionSumByDay(Long accountId, LocalDate date);

    @Query(value = "select sum(transaction.sum)\n" +
            "from transaction\n" +
            "         inner join vault v on transaction.vault_id = v.id\n" +
            "         inner join account a on v.account_id = a.id\n" +
            "         inner join transaction_tag tt on transaction.transaction_tag_id = tt.id\n" +
            "where account_id = ?1\n" +
            "  and tt.getting = false\n" +
            "  and user_id = ?2\n" +
            "and date between ?3 and ?4", nativeQuery = true)
    Double getTransactionSunByUser(Long accountId, String userId, LocalDate start, LocalDate end);
}
