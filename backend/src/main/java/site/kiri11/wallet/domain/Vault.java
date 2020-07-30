package site.kiri11.wallet.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
@Data
public class Vault implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Id.class})
    private Long id;

    @JsonView(Views.IdName.class)
    private String name;
    @JsonView(Views.IdName.class)
    private Double sum;

    @JsonView(Views.Full.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

//    @OneToMany(mappedBy = "vault")
//    private Set<Transaction> transactions;
}
