package site.kiri11.wallet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Category;
import site.kiri11.wallet.domain.Item;

import java.time.LocalDate;

public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query(value = "select sum(price) from item inner join category_tags on tag_id = tags_id inner join transaction on item.transaction_id = transaction.id where category_id = ?1 and date between ?2 and ?3", nativeQuery = true)
    Double getCategorySum(Long categoryId, LocalDate start, LocalDate end);

    @Query(value = "select sum(price) from item\n" +
            "                  inner join tag t on item.tag_id = t.id\n" +
            "                  inner join transaction t2 on item.transaction_id = t2.id\n" +
            "where t.account_id = ?1 and tag_id = ?2 and date between ?3 and ?4", nativeQuery = true)
    Double getTagSum(Long accountId, Long tagId, LocalDate start, LocalDate end);



}
