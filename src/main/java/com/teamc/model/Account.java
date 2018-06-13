package com.teamc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Account")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountGenerator")
    @SequenceGenerator(name = "accountGenerator", sequenceName = "accountSeq")
    @Column(name = "accountID", updatable = false, nullable = false)
    private Long accountID;

    private String name;

    private Double amount;

    public Account(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "accountID=" + accountID +
//                ", name='" + name + '\'' +
//                ", amount=" + amount +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return Objects.equals(accountID, account.accountID) &&
//                Objects.equals(name, account.name) &&
//                Objects.equals(amount, account.amount);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(accountID, name, amount);
//    }
}
