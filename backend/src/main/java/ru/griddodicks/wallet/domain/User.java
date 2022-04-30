package ru.griddodicks.wallet.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "usr")
@Data
public class User implements Serializable {
    @Id
    @JsonView(Views.Id.class)
    private String id;
    private String name;
    @JsonView(Views.Mid.class)
    private String email;
    @JsonView(Views.Mid.class)
    private String userpic;
    @JsonView(Views.Mid.class)
    private String gender;
    @JsonView(Views.Mid.class)
    private String locale;
    @JsonView(Views.Mid.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

    @ManyToOne
    @JsonIgnore
    private Account defaultAccount;

}
