package com.example.banking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_ID_GEN")
    @SequenceGenerator(name = "ACCOUNT_ID_GEN", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    private long accountId;

    private double balance;
    private double lastTransactionAmount;

    private String accountType;

    @ManyToOne
    @JoinColumn(name = "FK_CUST_ID")
    @JsonIgnore
    private Customer customer;

}
