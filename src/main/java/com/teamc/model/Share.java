package com.teamc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Share")
@Data
@NoArgsConstructor
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shareGenerator")
    @SequenceGenerator(name="shareGenerator", sequenceName = "shareSeq")
    @Column(name = "shareID", updatable = false, nullable = false)
    private Long shareID;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    private Double available;

    @NotNull
    private Double price;

    @NotNull
    private String fullName;

    public Share(String name, String fullName, double available, double price) {
        this.name = name;
        this.available = available;
        this.price = price;
        this.fullName = fullName;
    }
}
