package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.entity.ReceiverAccount;
import com.archexc.acctmgmtservice.model.AccountEvent;
import com.archexc.acctmgmtservice.model.ReceiverAccountDTO;
import com.archexc.acctmgmtservice.model.ReceiverStatusChangeRequest;
import com.archexc.acctmgmtservice.repository.ReceiverAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiverService {

    private final ReceiverAccountRepository receiverAccountRepository;
    private final KafkaProducerService kafkaProducerService;

    public ReceiverService(ReceiverAccountRepository receiverAccountRepository, KafkaProducerService kafkaProducerService) {
        this.receiverAccountRepository = receiverAccountRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public ReceiverAccountDTO createReceiver(ReceiverAccountDTO receiverAccountDTO) {
        ReceiverAccount receiverAccount = new ReceiverAccount();
        receiverAccount.setBankAccountId(receiverAccountDTO.getBankAccountId());
        receiverAccount.setReceiverName(receiverAccountDTO.getReceiverName());
        receiverAccount.setReceiverAccountNumber(receiverAccountDTO.getReceiverAccountNumber());
        receiverAccount.setReceiverBankName(receiverAccountDTO.getReceiverBankName());
        receiverAccount.setReceiverCurrency(receiverAccountDTO.getReceiverCurrency());
        receiverAccount.setStatus(receiverAccountDTO.getStatus());
        receiverAccount.setCreatedAt(LocalDateTime.now());

        ReceiverAccount savedReceiverAccount = receiverAccountRepository.save(receiverAccount);
        kafkaProducerService.sendAlertMessage("alert-topic", AccountEvent.builder()
                        .receiverName(savedReceiverAccount.getReceiverName())
                        .receiverAccountNumber(savedReceiverAccount.getReceiverAccountNumber())
                        .receiverBankName(savedReceiverAccount.getReceiverBankName())
                        .receiverCurrency(savedReceiverAccount.getReceiverCurrency())
                        .status(savedReceiverAccount.getStatus())
                        .bankAccountId(savedReceiverAccount.getBankAccountId())
                .build());
        return convertToDto(savedReceiverAccount);
    }

    public List<ReceiverAccountDTO> getAllReceivers() {
        return receiverAccountRepository.findAll().stream().map(this::convertToDto).toList();
    }


    public ReceiverAccountDTO statusUpdate(ReceiverStatusChangeRequest request) {
        Optional<ReceiverAccount> receiverAccountOpt = receiverAccountRepository.findById(request.getReceiverId());
        if(receiverAccountOpt.isPresent()) {
            ReceiverAccount receiverAccount = receiverAccountOpt.get();
            receiverAccount.setStatus(request.getStatus());
            receiverAccount = receiverAccountRepository.save(receiverAccount);
            return convertToDto(receiverAccount);
        } else {
            throw new RuntimeException("Receiver not found with id: " + request.getReceiverId());
        }
    }

    private ReceiverAccountDTO convertToDto(ReceiverAccount receiverAccount) {
        ReceiverAccountDTO dto = new ReceiverAccountDTO();
        dto.setReceiverId(receiverAccount.getReceiverId());
        dto.setBankAccountId(receiverAccount.getBankAccountId());
        dto.setReceiverName(receiverAccount.getReceiverName());
        dto.setReceiverAccountNumber(receiverAccount.getReceiverAccountNumber());
        dto.setReceiverBankName(receiverAccount.getReceiverBankName());
        dto.setReceiverCurrency(receiverAccount.getReceiverCurrency());
        dto.setStatus(receiverAccount.getStatus());
        return dto;
    }
}
