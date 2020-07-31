package site.kiri11.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Item;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.Transaction;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.dto.ItemDto;
import site.kiri11.wallet.dto.TransactionDto;
import site.kiri11.wallet.service.ItemService;

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
