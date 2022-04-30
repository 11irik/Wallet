package ru.griddodicks.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griddodicks.wallet.domain.Item;
import ru.griddodicks.wallet.domain.Transaction;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.dto.TransactionDto;
import ru.griddodicks.wallet.service.ItemService;

import java.util.Set;

@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    //fixme
    @PostMapping("transaction")
    public Set<Item> getTransactionItems(@RequestBody Transaction transaction) {
        return itemService.getItemsByTransaction(transaction.getId());
    }

    @PostMapping
    public Item create(@RequestBody TransactionDto dto, @AuthenticationPrincipal User user) {
        return itemService.save(dto.getItemName(), dto.getTagId(), dto.getItemPrice(), dto.getTransactionId());
    }

}
