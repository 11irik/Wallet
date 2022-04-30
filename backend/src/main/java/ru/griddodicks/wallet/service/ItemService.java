package ru.griddodicks.wallet.service;

import org.springframework.stereotype.Service;
import ru.griddodicks.wallet.domain.Item;
import ru.griddodicks.wallet.domain.Tag;
import ru.griddodicks.wallet.domain.Transaction;
import ru.griddodicks.wallet.repo.ItemRepo;

import java.util.Set;

@Service
public class ItemService {
    private final ItemRepo itemRepo;
    private final TransactionService transactionService;
    private final TagService tagService;

    public ItemService(ItemRepo itemRepo, TransactionService transactionService, TagService tagService) {
        this.itemRepo = itemRepo;
        this.transactionService = transactionService;
        this.tagService = tagService;
    }

    public Set<Item> getItemsByTransaction(Long id) {

        return itemRepo.findByTransaction(transactionService.getById(id));
    }

    public Item save(String itemName, Long tagId, Double price, Long transactionId) {
        Transaction transaction = transactionService.getById(transactionId);

        if (itemName == null || "".equals(itemName)) {
            throw new IllegalArgumentException();
        }

        Tag tagDb = null;
        if (tagId != null) {
            tagDb = tagService.findById(tagId);
        }

        Item newItem = new Item();
        newItem.setName(itemName);
        newItem.setPrice(price);

        newItem = itemRepo.save(newItem);
        newItem.setTransaction(transaction);
        newItem.setTag(tagDb);
        newItem = itemRepo.save(newItem);

        return newItem;
    }
}
