package com.example.banking.controllers;

import com.example.banking.models.Customer;
import com.example.banking.service.CustomerService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) throws MessagingException, UnsupportedEncodingException {
        Customer createdCustomer = customerService.createCustomer(customer);
        return createdCustomer;
    }
    @GetMapping("/customers")
    public String getCustomers() {
        return "";
    }
}
