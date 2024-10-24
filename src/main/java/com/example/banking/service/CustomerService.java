package com.example.banking.service;

import com.example.banking.models.Corporate;
import com.example.banking.models.Customer;
import com.example.banking.models.Individual;
import com.example.banking.models.SoleProprietorship;
import com.example.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        if (customer instanceof Corporate) {
            customer = (Corporate) customer;
        }else if(customer instanceof SoleProprietorship){
            customer = (SoleProprietorship) customer;
        }else if(customer instanceof Individual){
            customer = (Individual) customer;
        }
        return customerRepository.save(customer);
    }
}
