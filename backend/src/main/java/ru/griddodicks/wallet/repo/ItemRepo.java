package ru.griddodicks.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.griddodicks.wallet.domain.Item;
import ru.griddodicks.wallet.domain.Transaction;

import java.time.LocalDate;
import java.util.Set;

public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query(value = "select sum(price) from item inner join category_tags on tag_id = tags_id inner join transaction on item.transaction_id = transaction.id where category_id = ?1 and date between ?2 and ?3", nativeQuery = true)
    Double getCategorySum(Long categoryId, LocalDate start, LocalDate end);

    @Query(value = "select sum(price) from item\n" +
            "                  inner join tag t on item.tag_id = t.id\n" +
            "                  inner join transaction t2 on item.transaction_id = t2.id\n" +
            "where t.account_id = ?1 and tag_id = ?2 and date between ?3 and ?4", nativeQuery = true)
    Double getTagSum(Long accountId, Long tagId, LocalDate start, LocalDate end);

    Set<Item> findByTransaction(Transaction transaction);


}
