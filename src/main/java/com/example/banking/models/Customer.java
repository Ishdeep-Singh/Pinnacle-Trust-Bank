package com.example.banking.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "customerType"  // JSON field to distinguish types
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Individual.class, name = "Individual"),
        @JsonSubTypes.Type(value = Corporate.class, name = "Corporate"),
        @JsonSubTypes.Type(value = SoleProprietorship.class, name = "Sole Proprietorship"),
        // Add other subtypes as needed
})
public abstract class  Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOMER_ID_GEN")
    @SequenceGenerator(name = "CUSTOMER_ID_GEN", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
    private long customerId;
    private String name;
    private String address;
    private String email;
    private String phone;

    // Common fields
    private Date dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();
    @JsonProperty("TIN_SSN_SIN")
    private int TIN_SSN_SIN;
    private String nationality;
    private float creditScore;
    private String riskProfile;

    @JsonProperty("customerType")
    private String customerType;

    public Customer() {
    }

    // Constructor for Individual and Sole Customer
    public Customer(String name, String address, String email, String phone, String customerType, Date dateOfBirth, int TIN_SSN_SIN, String nationality, float creditScore, String riskProfile) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.customerType = customerType;
        this.dateOfBirth = dateOfBirth;
        this.TIN_SSN_SIN = TIN_SSN_SIN;
        this.nationality = nationality;
        this.creditScore = creditScore;
        this.riskProfile = riskProfile;
    }

    // Constructor for Corporate Customer
    public Customer(String name, String address, String email, String phone, String customerType, int TIN_SSN_SIN, String nationality, float creditScore, String riskProfile) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.customerType = customerType;
        this.TIN_SSN_SIN = TIN_SSN_SIN;
        this.nationality = nationality;
        this.creditScore = creditScore;
        this.riskProfile = riskProfile;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getCustomerType() {
        return email;
    }

    // Common getters
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getTIN_SSN_SIN() {
        return TIN_SSN_SIN;
    }

    public String getNationality() {
        return nationality;
    }

    public float getCreditScore() {
        return creditScore;
    }

    public String getRiskProfile() {
        return riskProfile;
    }
}
