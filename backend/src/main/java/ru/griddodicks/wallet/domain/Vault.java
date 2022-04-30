package ru.griddodicks.wallet.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
