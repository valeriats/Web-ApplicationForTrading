package com.teamc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "TransactionUserService")
@Data
@NoArgsConstructor
public class TransactionUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactionGenerator")
    @SequenceGenerator(name="transactionGenerator", sequenceName = "transactionSeq")
    @Column(name = "transactionID", updatable = false, nullable = false)
    private Long id;

    private Long userId;

    private String shareName;

    private double count;

    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    public TransactionUser(Long userId, String shareName, double count, double price, Date dateTime) {
        this.userId = userId;
        this.shareName = shareName;
        this.count = count;
        this.price = price;
        this.dateTime = dateTime;
    }
}
