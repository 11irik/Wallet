package site.kiri11.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.Tag;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.dto.TagDto;
import site.kiri11.wallet.service.AccountService;
import site.kiri11.wallet.service.TagService;

import java.util.Set;

@RestController
@RequestMapping("tag")
public class TagController {

    private final TagService tagService;
    private final AccountService accountService;

    @Autowired
    public TagController(TagService tagService, AccountService accountService) {

        this.tagService = tagService;
        this.accountService = accountService;
    }

    @PostMapping
    public Tag create(@RequestBody TagDto dto, @AuthenticationPrincipal User user) {
        return tagService.create(dto.getName(), dto.getAccountId(), user);
    }

    @PostMapping("account")
    public Set<Tag> getAccountTags(@RequestBody Account account, @AuthenticationPrincipal User user) {
        return tagService.findByAccount(account, user);
    }

}
