package ru.griddodicks.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.griddodicks.wallet.domain.Account;
import ru.griddodicks.wallet.domain.Tag;
import ru.griddodicks.wallet.domain.User;
import ru.griddodicks.wallet.repo.TagRepo;

import java.util.Set;

@Service
public class TagService {
    private final TagRepo tagRepo;
    private final AccountService accountService;

    @Autowired
    public TagService(TagRepo tagRepo, AccountService accountService) {
        this.tagRepo = tagRepo;
        this.accountService = accountService;
    }

    public Tag findById(Long id) {
        return tagRepo.findById(id).get();
    }

    public Tag create(String tagName, Long accountId, User user) {
        Account account = accountService.findById(accountId);

        Tag tag = new Tag();
        tag.setName(tagName);
        Tag savedTag = tagRepo.save(tag);
        savedTag.setAccount(account);
        return tagRepo.save(savedTag);
    }

    public Set<Tag> findByAccount(Account account, User user) {
        return tagRepo.findByAccount(account);
    }
}
