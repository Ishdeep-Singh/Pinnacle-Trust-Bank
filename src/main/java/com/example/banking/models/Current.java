package com.example.banking.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Current extends Account{
    @NotNull(message = "Minimum Initial deposit should not be null")
    private double minimumInitialDeposit;
    private float defaultInterestRate;

    public Current(double minimumInitialDeposit, float defaultInterestRate) {
        this.minimumInitialDeposit = minimumInitialDeposit;
        this.defaultInterestRate = defaultInterestRate;
    }

    public Current() {
    }

    public double getMinimumInitialDeposit() {
        return minimumInitialDeposit;
    }

    public void setMinimumInitialDeposit(double minimumInitialDeposit) {
        this.minimumInitialDeposit = minimumInitialDeposit;
        this.setBalance(minimumInitialDeposit);
    }

    public float getDefaultInterestRate() {
        return defaultInterestRate;
    }

    public void setDefaultInterestRate(float defaultInterestRate) {
        this.defaultInterestRate = defaultInterestRate;
    }
}
