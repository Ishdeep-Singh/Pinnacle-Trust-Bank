package com.example.banking.service;

import com.example.banking.exceptions.CustomerNotFoundException;
import com.example.banking.models.Account;
import com.example.banking.models.Current;
import com.example.banking.models.Customer;
import com.example.banking.models.Saving;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(long customerId, Account account) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(customerId).get();
        if(existingCustomer == null){
            throw new CustomerNotFoundException("Customer with the passed customerId:"+customerId+" is not found");
        }
        if(account instanceof Saving){
            account = (Saving) account;
        }
        if(account instanceof Current){
            account = (Current) account;
        }
        account.setCustomer(existingCustomer);
        existingCustomer.linkAccount(account);
        customerRepository.save(existingCustomer);
        return account;
    }
}
