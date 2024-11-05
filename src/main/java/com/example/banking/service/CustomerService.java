package com.example.banking.service;

import com.example.banking.models.Corporate;
import com.example.banking.models.Customer;
import com.example.banking.models.Individual;
import com.example.banking.models.SoleProprietorship;
import com.example.banking.repository.CustomerRepository;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private JavaMailSender mailSender;

    public CustomerService(CustomerRepository customerRepository, JavaMailSender mailSender) {
        this.customerRepository = customerRepository;
        this.mailSender = mailSender;
    }

    public Customer createCustomer(Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException {
        if (customer instanceof Corporate) {
            customer = (Corporate) customer;
        }else if(customer instanceof SoleProprietorship){
            customer = (SoleProprietorship) customer;
        }else if(customer instanceof Individual){
            customer = (Individual) customer;
        }
        String randomCode = EmailService.RequiredString(64);
        customer.setVerificationCode(randomCode);
        Customer createdCustomer = customerRepository.save(customer);
        EmailService.sendVerificationEmail(createdCustomer, mailSender, siteURL);
        return createdCustomer;

    }

    public boolean verifyUser(String verificationCode) {
        Customer user = customerRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();

            if (user.getSignedUpTimeStamp().isAfter(now.minusHours(24))) {
                // Within 24 hours
                user.setVerificationCode(null);
                user.setEnabled(true);
                customerRepository.save(user);
            } else {
                // More than 24 hours
                return false;
            }

            return true;
        }

    }
}
