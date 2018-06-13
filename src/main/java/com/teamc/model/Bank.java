package com.teamc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Bank")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"shares", "users"})
@ToString(exclude = {"shares", "users"})
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankGenerator")
    @SequenceGenerator(name = "bankGenerator", sequenceName = "bankSeq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    private String bik;

    private String kpp;

    private String inn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private List<Share> shares;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private List<User> users;

    public Bank(String name, String bik, String kpp, String inn, List<Share> shares, List<User> users) {
        this.name = name;
        this.bik = bik;
        this.kpp = kpp;
        this.inn = inn;
        this.shares = shares;
        this.users = users;
    }

//    @Override
//    public String toString() {
//        return "Bank{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", bik='" + bik + '\'' +
//                ", kpp='" + kpp + '\'' +
//                ", inn='" + inn + '\'' +
//                //", shares=" + shares +
//                //", users=" + users +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Bank)) return false;
//        Bank bank = (Bank) o;
//        return Objects.equals(getId(), bank.getId()) &&
//                Objects.equals(getName(), bank.getName()) &&
//                Objects.equals(getBik(), bank.getBik()) &&
//                Objects.equals(getKpp(), bank.getKpp()) &&
//                Objects.equals(getInn(), bank.getInn());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), getBik(), getKpp(), getInn());
//    }
}
