package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount registerAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }


}