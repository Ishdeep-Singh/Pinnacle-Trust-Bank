package com.example.banking.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Saving extends Account{
    @NotNull(message = "Minimum Initial deposit should not be null")
    private double minimumInitialDeposit;
    private float defaultInterestRate;

    public Saving(double minimumInitialDeposit, float defaultInterestRate) {
        this.minimumInitialDeposit = minimumInitialDeposit;
        this.defaultInterestRate = defaultInterestRate;
    }

    public Saving() {
    }

    public double getMinimumInitialDeposit() {
        return minimumInitialDeposit;
    }

    public void setMinimumInitialDeposit(double minimumInitialDeposit) {
        this.minimumInitialDeposit = minimumInitialDeposit;
        if (this.getBalance() == 0) {
            this.setBalance(minimumInitialDeposit);
        }
    }

    public float getDefaultInterestRate() {
        return defaultInterestRate;
    }

    public void setDefaultInterestRate(float defaultInterestRate) {
        this.defaultInterestRate = defaultInterestRate;
    }
}
