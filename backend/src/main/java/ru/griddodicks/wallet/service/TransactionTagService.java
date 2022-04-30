package ru.griddodicks.wallet.service;

import org.springframework.stereotype.Service;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.TransactionTag;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.repo.TransactionTagRepo;

import java.util.List;

@Service
public class TransactionTagService {
    private final TransactionTagRepo transactionTagRepo;

    public TransactionTagService(TransactionTagRepo transactionTagRepo) {
        this.transactionTagRepo = transactionTagRepo;
    }

    public List<TransactionTag> getAll(Account account, User user) {//fixme type
        return transactionTagRepo.findAll();
    }
}
