package site.kiri11.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Category;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.dto.CategoryDto;
import site.kiri11.wallet.repo.AccountRepo;
import site.kiri11.wallet.repo.CategoryRepo;
import site.kiri11.wallet.repo.TagRepo;
import site.kiri11.wallet.service.CategoryService;

import java.util.Set;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category create(@RequestBody CategoryDto dto, @AuthenticationPrincipal User user) {
        return categoryService.create(dto.getAccountId(), dto.getName(), user);
    }

    @PostMapping("tag")
    public Category addTag(@RequestBody CategoryDto categoryDto, @AuthenticationPrincipal User user) {
        return categoryService.addTag(categoryDto.getCategoryId(), categoryDto.getTagId(), user);
    }

    @PostMapping("tag/remove")
    public Category removeTag(@RequestBody CategoryDto categoryDto, @AuthenticationPrincipal User user) {
        return categoryService.removeTag(categoryDto.getCategoryId(), categoryDto.getTagId(), user);
    }

    @PostMapping("account")
    public Set<Category> getAccountCategories(@RequestBody Account account, @AuthenticationPrincipal User user) {
        return categoryService.getAccountCategories(account, user);
    }
}
