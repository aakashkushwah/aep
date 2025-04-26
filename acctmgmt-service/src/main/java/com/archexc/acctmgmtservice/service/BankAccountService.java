package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.model.BankAccountDTO;
import com.archexc.acctmgmtservice.model.NotificationEvent;
import com.archexc.acctmgmtservice.model.UserDetail;
import com.archexc.acctmgmtservice.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public BankAccountDTO registerAccount(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
        bankAccount.setAccountType(bankAccountDTO.getAccountType());
        bankAccount.setBalance(bankAccountDTO.getBalance());
        bankAccount.setCurrency(bankAccountDTO.getCurrency());
        bankAccount.setCustomerId(bankAccountDTO.getCustomerId());
        bankAccount.setCreatedAt(LocalDateTime.now());

        BankAccount savedBa = bankAccountRepository.save(bankAccount);

        // Send notification to the user
        UserDetail userDetail = cacheService.getUserDetailsByCustomerId(savedBa.getCustomerId());
        NotificationEvent notificationEvent = new NotificationEvent(userDetail.getEmail(), "NOTIFICATION",
                "Your new Bank account "+ savedBa.getAccountId()+" is created.");
        kafkaProducerService.sendNotificationMessage(notificationEvent);

        return BankAccountDTO.builder()
                .accountId(savedBa.getAccountId())
                .accountNumber(savedBa.getAccountNumber())
                .customerId(savedBa.getCustomerId())
                .accountType(savedBa.getAccountType())
                .balance(savedBa.getBalance())
                .currency(savedBa.getCurrency())
                .createdAt(savedBa.getCreatedAt())
                .build();
    }


}