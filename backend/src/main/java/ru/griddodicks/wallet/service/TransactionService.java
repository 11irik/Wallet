package ru.griddodicks.wallet.service;

import org.springframework.stereotype.Service;
import ru.griddodicks.wallet.domain.Transaction;
import ru.griddodicks.wallet.domain.TransactionTag;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.domain.Vault;
import ru.griddodicks.wallet.repo.TagRepo;
import ru.griddodicks.wallet.repo.TransactionRepo;
import ru.griddodicks.wallet.repo.TransactionTagRepo;
import ru.griddodicks.wallet.repo.VaultRepo;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final TransactionTagRepo transactionTagRepo;
    private final TagRepo tagRepo;
    private final VaultRepo vaultRepo;

    public TransactionService(TransactionRepo transactionRepo, TransactionTagRepo transactionTagRepo, TagRepo tagRepo, VaultRepo vaultRepo) {

        this.transactionRepo = transactionRepo;
        this.transactionTagRepo = transactionTagRepo;
        this.tagRepo = tagRepo;
        this.vaultRepo = vaultRepo;
    }

    public Transaction create(Transaction transaction, TransactionTag transactionTag, Vault vault, User user) {

        if (transaction.getDescription() == null || "".equals(transaction.getDescription()) || transaction.getSum() == null) {
            throw new IllegalArgumentException();
        }

        transaction = transactionRepo.save(transaction);

        transactionTag = transactionTagRepo.getOne(transactionTag.getId());

        vault = vaultRepo.getOne(vault.getId());

        Double vaultSum = vault.getSum();
        Double transactionSum = Math.abs(transaction.getSum());

        if (transactionTag.getGetting()) {
            vaultSum += transactionSum;
        } else {
            vaultSum -= transactionSum;
        }

        vault.setSum(vaultSum);
        vaultRepo.save(vault);

        transaction.setVault(vault);
        transaction.setTransactionTag(transactionTag);
        transaction.setUser(user);

        return transactionRepo.save(transaction);
    }

    public Transaction getById(Long id) {
        return transactionRepo.getOne(id);
    }
}
