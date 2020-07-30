package site.kiri11.wallet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Id.class})
    private Long id;

    @JsonView(Views.IdName.class)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonView(Views.Mid.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @JsonView(Views.Full.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_users",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<User>();
}
