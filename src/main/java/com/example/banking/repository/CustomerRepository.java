package com.example.banking.repository;

import com.example.banking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Query("SELECT u FROM Customer u WHERE u.verificationCode = ?1")
    public Customer findByVerificationCode(String code);
}
