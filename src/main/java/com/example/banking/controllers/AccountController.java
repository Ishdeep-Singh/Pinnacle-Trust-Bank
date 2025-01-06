package com.example.banking.controllers;

import com.example.banking.exceptions.CustomerNotFoundException;
import com.example.banking.models.Account;
import com.example.banking.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account, @RequestParam Long customerId) throws CustomerNotFoundException {
        if(customerId == null || customerId.toString().isEmpty()){
            System.out.println("CustomerId is required");
        }
        Account createdAccount = accountService.createAccount(customerId, account);
        return createdAccount;
    }

    @GetMapping("/")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
