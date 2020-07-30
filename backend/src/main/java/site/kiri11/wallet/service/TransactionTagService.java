package site.kiri11.wallet.service;

import org.springframework.stereotype.Service;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.TransactionTag;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.repo.TransactionTagRepo;

import java.util.List;
import java.util.Set;

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
