package com.teamc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"userID", "address", "firstAcc", "username", "password", "active", "prizStatus", "mail", "roles", "accountList"})
@ToString(exclude = {"roles"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGenerator")
    @SequenceGenerator(name = "userGenerator", sequenceName = "userSeq")
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userID;

    private String firstName;

    private String secondName;

    private String address;

    private double firstAcc;

    private String username;

    @JsonBackReference
    private String password;

    private boolean active;

    private boolean prizStatus; //1 если др и сегодня дать подарок

    private String mail;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;


    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Account> accountList;


    public User(String firstName, String secondName, String address, List<Account> accountList) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.accountList = accountList;
    }


    public User(String firstName, String secondName, String address, String username, String password, boolean active, Set<Role> roles, LocalDate birthday, List<Account> accountList, String mail) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.birthday = birthday;
        this.accountList = accountList;
        this.mail = mail;
    }

    public User(String username, List<Account> accountList) {
        this.username = username;
        this.accountList = accountList;
    }

    public double getAccount(String nameAccounts) {
        List<Account> accs = this.accountList;
        Account acc = accs.stream()
                .filter(x -> nameAccounts.equals(x.getName()))
                .findFirst()
                .orElse(null);
        if (acc != null) {
            return acc.getAmount();
        }
        return 0;
    }

}
