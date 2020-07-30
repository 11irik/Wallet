package site.kiri11.wallet.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.Transaction;
import site.kiri11.wallet.domain.TransactionTag;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.domain.Vault;
import site.kiri11.wallet.dto.TransactionDto;
import site.kiri11.wallet.repo.TagRepo;
import site.kiri11.wallet.repo.TransactionRepo;
import site.kiri11.wallet.repo.TransactionTagRepo;
import site.kiri11.wallet.repo.VaultRepo;

import java.time.LocalDate;

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
        }
        else {
            vaultSum -= transactionSum;
        }

        vault.setSum(vaultSum);
        vaultRepo.save(vault);

        transaction.setVault(vault);
        transaction.setTransactionTag(transactionTag);
        transaction.setUser(user);

        return transactionRepo.save(transaction);
    }
}
