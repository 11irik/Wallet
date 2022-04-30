package ru.griddodicks.wallet.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griddodicks.wallet.domain.TransactionTag;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.service.TransactionTagService;

import java.util.List;

@RestController
@RequestMapping("transaction/tag")
public class TransactionTagController {
    private final TransactionTagService transactionTagService;

    public TransactionTagController(TransactionTagService transactionTagService) {
        this.transactionTagService = transactionTagService;
    }

    @GetMapping
    public List<TransactionTag> get(@AuthenticationPrincipal User user) {
        return transactionTagService.getAll(null, user); //fixme
    }
}
