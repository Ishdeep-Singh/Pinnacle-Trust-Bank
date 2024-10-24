package com.example.banking.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Corporate extends Customer{
    private String companyRegistrationNumber;
    private String businessStructure;
    private String industryType;
    private String bOD_authSign;
    private double annualRevenue;
    private int noOfEmployees;
    private String registeredOffice;
    @JsonProperty("ein")
    @Column(name="ein")
    private String ein; //Employer Identification Number
    private String financialStatements;


    public Corporate(){
        super();
    }

    public Corporate(String name, String address, String email, String phoneNumber, String customerType, String companyRegistrationNumber, String businessStructure, String industryType, String bOD_authSign, double annualRevenue, int noOfEmployees, String registeredOffice, String ein, String financialStatements, int TIN_SSN_SIN, String nationality, float creditScore, String riskProfile) {
        super(name, address, email, phoneNumber, customerType, TIN_SSN_SIN, nationality, creditScore, riskProfile);
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.businessStructure = businessStructure;
        this.industryType = industryType;
        this.bOD_authSign = bOD_authSign;
        this.annualRevenue = annualRevenue;
        this.noOfEmployees = noOfEmployees;
        this.registeredOffice = registeredOffice;
        this.ein = ein;
        this.financialStatements = financialStatements;
    }

    public String getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public String getBusinessStructure() {
        return businessStructure;
    }

    public String getIndustryType() {
        return industryType;
    }

    public String getbOD_authSign() {
        return bOD_authSign;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public String getFinancialStatements() {
        return financialStatements;
    }

    public String getRegisteredOffice() {
        return registeredOffice;
    }

    public String getEin() {
        return ein;
    }
}
