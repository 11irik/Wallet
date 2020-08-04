package site.kiri11.wallet.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.kiri11.wallet.domain.Account;
import site.kiri11.wallet.domain.User;
import site.kiri11.wallet.domain.Views;
import site.kiri11.wallet.repo.AccountRepo;
import site.kiri11.wallet.repo.CategoryRepo;
import site.kiri11.wallet.repo.MessageRepo;
import site.kiri11.wallet.repo.TagRepo;
import site.kiri11.wallet.repo.TransactionTagRepo;
import site.kiri11.wallet.repo.VaultRepo;

import java.util.HashMap;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepo messageRepo;
    private final AccountRepo accountRepo;
    private final VaultRepo vaultRepo;
    private final TransactionTagRepo transactionTagRepo;
    private final CategoryRepo categoryRepo;
    private final TagRepo tagRepo;

    @Value("${spring.profiles.active:prod}")
    private String profile;

    @Autowired
    public MainController(MessageRepo messageRepo, AccountRepo accountRepo, VaultRepo vaultRepo, TransactionTagRepo transactionTagRepo, CategoryRepo categoryRepo, TagRepo tagRepo) {
        this.messageRepo = messageRepo;
        this.accountRepo = accountRepo;
        this.vaultRepo = vaultRepo;
        this.transactionTagRepo = transactionTagRepo;

        this.categoryRepo = categoryRepo;
        this.tagRepo = tagRepo;
    }

    @GetMapping
    @JsonView(Views.Mid.class)
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
            Set<Account> accounts = accountRepo.findByUsers(user);
            data.put("userAccounts", accounts);

            data.put("defaultAccount", user.getDefaultAccount());
            data.put("categories", categoryRepo.findByAccount(user.getDefaultAccount()));
            data.put("tags", tagRepo.findByAccount(user.getDefaultAccount()));
            data.put("vaults", vaultRepo.findByAccount(user.getDefaultAccount()));
            data.put("transactionTags", transactionTagRepo.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
