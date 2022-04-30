package ru.griddodicks.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.Category;
import ru.griddodicks.wallet.domain.Tag;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.repo.CategoryRepo;

import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final AccountService accountService;
    private final TagService tagService;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, AccountService accountService, TagService tagService) {
        this.categoryRepo = categoryRepo;
        this.accountService = accountService;
        this.tagService = tagService;
    }

    public Category create(Long accountId, String categoryName, User user) {
        Account account = accountService.findById(accountId);

        Category category = new Category();
        category.setName(categoryName);
        Category savedCategory = categoryRepo.save(category);
        savedCategory.setAccount(account);
        return categoryRepo.save(savedCategory);
    }

    public Category addTag(Long categoryId, Long tagId, User user) {
        Category categoryDb = categoryRepo.findById(categoryId).get();
        Tag tagDb = tagService.findById(tagId);
        categoryDb.getTags().add(tagDb);
        return categoryRepo.save(categoryDb);
    }

    public Category removeTag(Long categoryId, Long tagId, User user) {
        Category categoryDb = categoryRepo.findById(categoryId).get();
        Tag tagDb = tagService.findById(tagId);
        categoryDb.getTags().remove(tagDb);
        return categoryRepo.save(categoryDb);
    }

    public Set<Category> getAccountCategories(Account account, User user) {
        return categoryRepo.findByAccount(account);
    }
}
