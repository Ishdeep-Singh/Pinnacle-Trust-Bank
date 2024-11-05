package com.example.banking.controllers;

import com.example.banking.models.Customer;
import com.example.banking.service.CustomerService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String siteURL = request.getRequestURL().toString();
        siteURL = siteURL.replace(request.getServletPath(), "");
        Customer createdCustomer = customerService.createCustomer(customer, siteURL);
        return createdCustomer;
    }

    @GetMapping("/customers")
    public String getCustomers() {
        return "All Customers Data";
    }

    @GetMapping("/verify_customer")
    public String userVerification(@RequestParam("code") String code){
        if (customerService.verifyUser(code)) {
            return "Customer verified Successfully";
        }
        return "Customer verification failed due to either verification link expired or user is null or user is already enabled";

    }
}
