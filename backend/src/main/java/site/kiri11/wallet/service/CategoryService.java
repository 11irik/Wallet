package site.kiri11.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Category;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.repo.CategoryRepo;

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
        Tag tagDb = tagService.findById(tagId, user);
        categoryDb.getTags().add(tagDb);
        return categoryRepo.save(categoryDb);
    }

    public Category removeTag(Long categoryId, Long tagId, User user) {
        Category categoryDb = categoryRepo.findById(categoryId).get();
        Tag tagDb = tagService.findById(tagId, user);
        categoryDb.getTags().remove(tagDb);
        return categoryRepo.save(categoryDb);
    }

    public Set<Category> getAccountCategories(Account account, User user) {
        return categoryRepo.findByAccount(account);
    }
}
