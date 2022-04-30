package ru.griddodicks.wallet.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Id.class})
    private Long id;

    @JsonView(Views.IdName.class)
    private String name;

    @ManyToOne
    @JsonView(Views.Full.class)
    private Account account;
}
