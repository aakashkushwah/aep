package com.archexc.acctmgmtservice.controller;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.model.BankAccountDTO;
import com.archexc.acctmgmtservice.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<BankAccountDTO> registerAccount(@RequestBody BankAccountDTO bankAccount) {
        BankAccountDTO createdAccount = bankAccountService.registerAccount(bankAccount);
        return ResponseEntity.ok(createdAccount);
    }
}