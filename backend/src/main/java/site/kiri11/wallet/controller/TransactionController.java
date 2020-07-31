package site.kiri11.wallet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Category;
import site.kiri11.wallet.domain.Item;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.Transaction;
import site.kiri11.wallet.domain.TransactionTag;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.domain.Vault;
import site.kiri11.wallet.domain.Views;
import site.kiri11.wallet.dto.TransactionDto;
import site.kiri11.wallet.repo.AccountRepo;
import site.kiri11.wallet.repo.CategoryRepo;
import site.kiri11.wallet.repo.ItemRepo;
import site.kiri11.wallet.repo.TagRepo;
import site.kiri11.wallet.repo.TransactionRepo;
import site.kiri11.wallet.repo.TransactionTagRepo;
import site.kiri11.wallet.repo.UserDetailsRepo;
import site.kiri11.wallet.repo.VaultRepo;
import site.kiri11.wallet.service.TransactionService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("transaction")
@Slf4j
public class TransactionController {

    private final TransactionRepo transactionRepo;
    private final TransactionTagRepo transactionTagRepo;
    private final VaultRepo vaultRepo;
    private final AccountRepo accountRepo;
    private final ItemRepo itemRepo;
    private final TagRepo tagRepo;
    private final CategoryRepo categoryRepo;
    private final UserDetailsRepo userDetailsRepo;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionRepo transactionRepo, TransactionTagRepo transactionTagRepo, VaultRepo vaultRepo, AccountRepo accountRepo, ItemRepo itemRepo, TagRepo tagRepo, CategoryRepo categoryRepo, UserDetailsRepo userDetailsRepo, TransactionService transactionService) {

        this.transactionRepo = transactionRepo;
        this.transactionTagRepo = transactionTagRepo;
        this.vaultRepo = vaultRepo;
        this.accountRepo = accountRepo;
        this.itemRepo = itemRepo;
        this.tagRepo = tagRepo;


        this.categoryRepo = categoryRepo;
        this.userDetailsRepo = userDetailsRepo;
        this.transactionService = transactionService;
    }

    @PostMapping("account")
    public Set<Transaction> get(@RequestBody Account account, @AuthenticationPrincipal User user) {
//        Account accountDb = accountRepo.findById(id).get();
        return transactionRepo.findByVault_Account(account);
    }

    @PostMapping("request")
    public Set<Transaction> request(@RequestBody TransactionDto dto, @AuthenticationPrincipal User user) {
        LocalDate dateStart;
        LocalDate dateEnd;

        if (dto.getDateStart() == null) {
            throw new IllegalArgumentException();
        }
        else {
            dateStart = LocalDate.parse(dto.getDateStart());
        }

        if (dto.getDateEnd() == null) {
            dateEnd = dateStart;
        }
        else {
            dateEnd = LocalDate.parse(dto.getDateEnd());
        }

        Account accountDb = accountRepo.findById(dto.getAccountId()).get();

        Set<Transaction> transactions = transactionRepo.findByVault_AccountAndDateBetween(accountDb, dateStart, dateEnd);

        return transactions;
    }

    @PostMapping("group")
    public HashMap<Object, Object> getGroupData(@RequestBody TransactionDto dto, @AuthenticationPrincipal User user) {
        LocalDate dateStart;
        LocalDate dateEnd;

        if (dto.getDateStart() == null) {
            throw new IllegalArgumentException();
        }
        else {
            dateStart = LocalDate.parse(dto.getDateStart());
        }
        if (dto.getDateEnd() == null) {
            dateEnd = dateStart;
        }
        else {
            dateEnd = LocalDate.parse(dto.getDateEnd());
        }

        Account accountDb = accountRepo.findById(dto.getAccountId()).get();

        Set<Tag> tags = tagRepo.findByAccount(accountDb);
        Set<Category> categories = categoryRepo.findByAccount(accountDb);

        HashMap<Object, Object> values = new HashMap<>();
        for (Category category : categories) {
            values.put(category.getName(), itemRepo.getCategorySum(category.getId(), dateStart, dateEnd));
        }

        return values;
    }

    @PostMapping("tag")
    public HashMap<Object, Object> getTagData(@RequestBody TransactionDto dto, @AuthenticationPrincipal User user) {
        LocalDate dateStart;
        LocalDate dateEnd;

        if (dto.getDateStart() == null) {
            throw new IllegalArgumentException();
        }
        else {
            dateStart = LocalDate.parse(dto.getDateStart());
        }
        if (dto.getDateEnd() == null) {
            dateEnd = dateStart;
        }
        else {
            dateEnd = LocalDate.parse(dto.getDateEnd());
        }

        Account accountDb = accountRepo.findById(dto.getAccountId()).get();

        Set<Tag> tags = tagRepo.findByAccount(accountDb);


        HashMap<Object, Object> values = new HashMap<>();
        for (Tag tag : tags) {
            values.put(tag.getName(), itemRepo.getTagSum(accountDb.getId(), tag.getId(), dateStart, dateEnd));
        }

        return values;
    }

    @PostMapping("day")
    public HashMap<Object, Object> getDateData(@RequestBody TransactionDto dto, @AuthenticationPrincipal User user) {
        LocalDate dateStart;
        LocalDate dateEnd;

        if (dto.getDateStart() == null) {
            throw new IllegalArgumentException();
        }
        else {
            dateStart = LocalDate.parse(dto.getDateStart());
        }
        if (dto.getDateEnd() == null) {
            dateEnd = dateStart;
        }
        else {
            dateEnd = LocalDate.parse(dto.getDateEnd());
        }

        Account accountDb = accountRepo.findById(dto.getAccountId()).get();

        HashMap<Object, Object> values = new HashMap<>();

        while (dateStart.isBefore(dateEnd)) {
            values.put(dateStart, transactionRepo.getTransactionSumByDay(accountDb.getId(), dateStart));
            dateStart = dateStart.plusDays(1);
        }

        return values;
    }

    @PostMapping("user")
    public HashMap<Object, Object> getUserData(@RequestBody TransactionDto dto, @AuthenticationPrincipal User user) {
        LocalDate dateStart;
        LocalDate dateEnd;

        if (dto.getDateStart() == null) {
            throw new IllegalArgumentException();
        }
        else {
            dateStart = LocalDate.parse(dto.getDateStart());
        }
        if (dto.getDateEnd() == null) {
            dateEnd = dateStart;
        }
        else {
            dateEnd = LocalDate.parse(dto.getDateEnd());
        }

        Account accountDb = accountRepo.findById(dto.getAccountId()).get();

        Set<User> users = accountDb.getUsers();

        HashMap<Object, Object> values = new HashMap<>();

        int k = 1;
        for (User u : users) {
            values.put(u.getName()+"#"+k++, transactionRepo.getTransactionSunByUser(accountDb.getId(), u.getId(), dateStart, dateEnd));
        }

        return values;
    }


    @PostMapping
    public Transaction create(@RequestBody TransactionDto transactionDto, @AuthenticationPrincipal User user) {
        Transaction transaction = new Transaction();
        transaction.setDescription(transactionDto.getDescription());
        transaction.setDate(LocalDate.parse(transactionDto.getDate()));
        transaction.setSum(transactionDto.getSum());

        TransactionTag tag = new TransactionTag();
        tag.setId(transactionDto.getTransactionTagId());

        Vault vault = new Vault();
        vault.setId(transactionDto.getVaultId());

        return transactionService.create(transaction, tag, vault, user);
    }

}
