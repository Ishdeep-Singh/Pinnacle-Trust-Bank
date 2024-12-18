package com.example.banking.controllers;

import com.example.banking.models.Account;
import com.example.banking.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
