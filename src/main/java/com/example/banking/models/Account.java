package com.example.banking.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "accountType"  // JSON field to distinguish types
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Saving.class, name = "Saving"),
        @JsonSubTypes.Type(value = Current.class, name = "Current"),
        // Add other subtypes as needed
})
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_ID_GEN")
    @SequenceGenerator(name = "ACCOUNT_ID_GEN", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
    private long accountId;

    @JsonProperty("balance")
    private double balance;
    private double lastTransactionAmount;

    @JsonProperty("accountType")
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "FK_CUST_ID")
    @JsonBackReference
    private Customer customer;
    @PrePersist
    protected void setDefaultAccountType() {
        if (this.accountType == null) {
            this.accountType = this.getClass().getSimpleName(); // Default to the subclass name
        }
    }
    public Account() {
    }

    public Account(double balance, double lastTransactionAmount, String accountType) {
        this.balance = balance;
        this.lastTransactionAmount = lastTransactionAmount;
        this.accountType = accountType;
    }

    public long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLastTransactionAmount() {
        return lastTransactionAmount;
    }

    public void setLastTransactionAmount(double lastTransactionAmount) {
        this.lastTransactionAmount = lastTransactionAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
