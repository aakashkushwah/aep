package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.model.AccountEvent;
import com.archexc.acctmgmtservice.model.ReceiverStatusChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private ReceiverService receiverService;

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
    }

    @KafkaListener(topics = "account-topic", groupId = "test-group")
    public void consumeAccountEvent(AccountEvent accountEvent) {
        System.out.println("Consumed: " + accountEvent);
        if(accountEvent!= null && accountEvent.getStatus().equalsIgnoreCase("Banned")) {
            System.out.println("Account is banned");
            ReceiverStatusChangeRequest receiverStatusChangeRequest = new ReceiverStatusChangeRequest();
            receiverStatusChangeRequest.setReceiverAccountNumber(accountEvent.getReceiverAccountNumber());
            receiverStatusChangeRequest.setStatus("Banned");
            receiverService.banAccount(receiverStatusChangeRequest);
        }

    }
}

