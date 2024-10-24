package com.example.banking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Individual extends Customer{

    @Column(name="occupation")
    private String occupation;
    @Column(name="martialStatus")
    private String martialStatus;
    @Column(name="dependents")
    private int dependents;
    @Column(name="annualIncome")
    private double annualIncome;
    @Column(name="gender")
    private String gender;
    @Column(name="photoId")
    private String photoId;
    @Column(name="emergencyContactInfo")
    private String emergencyContactInfo;

    public Individual(){
        super();
    }

    public Individual(String name, String address, String email, String phone, String customerType, String occupation, String martialStatus, int dependents, double annualIncome, String gender, String photoId, String emergencyContactInfo, Date dateOfBirth, int TIN_SSN_SIN, String nationality, float creditScore, String riskProfile) {
        super(name, address, email, phone, customerType, dateOfBirth, TIN_SSN_SIN, nationality, creditScore, riskProfile);
        this.occupation = occupation;
        this.martialStatus = martialStatus;
        this.dependents = dependents;
        this.annualIncome = annualIncome;
        this.gender = gender;
        this.photoId = photoId;
        this.emergencyContactInfo = emergencyContactInfo;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public int getDependents() {
        return dependents;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public String getGender() {
        return gender;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getEmergencyContactInfo() {
        return emergencyContactInfo;
    }
}
