package site.kiri11.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Item;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.Transaction;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.dto.ItemDto;
import site.kiri11.wallet.repo.AccountRepo;
import site.kiri11.wallet.repo.ItemRepo;
import site.kiri11.wallet.repo.TagRepo;
import site.kiri11.wallet.repo.TransactionRepo;
import site.kiri11.wallet.repo.VaultRepo;

import java.util.Set;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    public ItemController() {

    }

//    @PostMapping
//    public Item create(@RequestBody ItemDto dto) {
//        Transaction transactionDb = transactionRepo.findById(dto.getTransactionId()).get();
//
//        return itemRepo.save(newItem);
//    }

}
