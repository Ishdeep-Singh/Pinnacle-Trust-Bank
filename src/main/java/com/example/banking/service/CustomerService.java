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

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private JavaMailSender mailSender;

    public CustomerService(CustomerRepository customerRepository, JavaMailSender mailSender) {
        this.customerRepository = customerRepository;
        this.mailSender = mailSender;
    }

    public Customer createCustomer(Customer customer) throws MessagingException, UnsupportedEncodingException {
        if (customer instanceof Corporate) {
            customer = (Corporate) customer;
        }else if(customer instanceof SoleProprietorship){
            customer = (SoleProprietorship) customer;
        }else if(customer instanceof Individual){
            customer = (Individual) customer;
        }
        Customer createdCustomer = customerRepository.save(customer);
        EmailService.sendVerificationEmail(createdCustomer, mailSender);
        return createdCustomer;

    }
}
