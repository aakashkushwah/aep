package com.archexc.acctmgmtservice.controller;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<BankAccount> registerAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdAccount = bankAccountService.registerAccount(bankAccount);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/receiver")
    public ResponseEntity<BankAccount> createReceiver(@RequestBody BankAccount receiverAccount) {
        BankAccount createdReceiver = bankAccountService.createReceiver(receiverAccount);
        return ResponseEntity.ok(createdReceiver);
    }

    @GetMapping("/receiver")
    public ResponseEntity<List<BankAccount>> getAllReceivers() {
        List<BankAccount> receivers = bankAccountService.getAllReceivers();
        return ResponseEntity.ok(receivers);
    }

    @DeleteMapping("/receiver/{id}")
    public ResponseEntity<Void> deleteReceiver(@PathVariable Long id) {
        bankAccountService.deleteReceiver(id);
        return ResponseEntity.noContent().build();
    }
}