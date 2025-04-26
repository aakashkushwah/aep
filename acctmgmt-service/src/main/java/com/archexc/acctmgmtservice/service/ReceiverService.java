package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.entity.ReceiverAccount;
import com.archexc.acctmgmtservice.model.ReceiverStatusChangeRequest;
import com.archexc.acctmgmtservice.repository.ReceiverAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiverService {

    private final ReceiverAccountRepository receiverAccountRepository;

    public ReceiverService(ReceiverAccountRepository receiverAccountRepository) {
        this.receiverAccountRepository = receiverAccountRepository;
    }

    public ReceiverAccount createReceiver(ReceiverAccount receiverAccount) {
        receiverAccountRepository.save(receiverAccount);
        return receiverAccount;
    }

    public List<ReceiverAccount> getAllReceivers() {
        return receiverAccountRepository.findAll();
    }


    public ReceiverAccount statusUpdate(ReceiverStatusChangeRequest request) {
        Optional<ReceiverAccount> receiverAccountOpt = receiverAccountRepository.findById(request.getReceiverId());
        if(receiverAccountOpt.isPresent()) {
            ReceiverAccount receiverAccount = receiverAccountOpt.get();
            receiverAccount.setStatus(request.getStatus());
            return receiverAccountRepository.save(receiverAccount);
        } else {
            throw new RuntimeException("Receiver not found with id: " + request.getReceiverId());
        }
    }
}
