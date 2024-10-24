package com.example.banking.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class SoleProprietorship extends Customer{
    private String businessName;
    private String ownerPersoalInfo;
    private String businessAddress;
    private double businessIncome;
    private String typeOfBusiness;
    private String businessRegDocs;
    @JsonProperty("TIN")
    private String TIN;

    public SoleProprietorship(){
        super();
    }

    public SoleProprietorship(String name, String address, String email, String phoneNumber, String customerType, String businessName, String ownerPersoalInfo, String businessAddress, double businessIncome, String typeOfBusiness, String businessRegDocs, String TIN, Date dateOfBirth, int TIN_SSN_SIN, String nationality, float creditScore, String riskProfile) {
        super(name, address, email, phoneNumber, customerType, dateOfBirth, TIN_SSN_SIN, nationality, creditScore, riskProfile);
        this.businessName = businessName;
        this.ownerPersoalInfo = ownerPersoalInfo;
        this.businessAddress = businessAddress;
        this.businessIncome = businessIncome;
        this.typeOfBusiness = typeOfBusiness;
        this.businessRegDocs = businessRegDocs;
        this.TIN = TIN;
    }
    public String getBusinessName() {
        return businessName;
    }

    public String getOwnerPersoalInfo() {
        return ownerPersoalInfo;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public double getBusinessIncome() {
        return businessIncome;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public String getBusinessRegDocs() {
        return businessRegDocs;
    }

    public String getTIN() {
        return TIN;
    }
}
