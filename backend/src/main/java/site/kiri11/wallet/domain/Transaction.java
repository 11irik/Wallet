package site.kiri11.wallet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Id.class})
    private Long id;

    @JsonView(Views.IdName.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonView(Views.IdName.class)
    private String description;
    @JsonView(Views.IdName.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Vault vault;
    @JsonView(Views.IdName.class)
    private Double sum;
    @JsonView(Views.IdName.class)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private TransactionTag transactionTag;

    @JsonView(Views.IdName.class)
    private Boolean takenInto;

    @JsonView({Views.IdName.class})
    @OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
    private Set<Item> Items = new HashSet<>();
}
