package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.model.AccountEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
    }

    @KafkaListener(topics = "account-topic", groupId = "test-group")
    public void consumeAccountEvent(AccountEvent accountEvent) {
        System.out.println("Consumed: " + accountEvent);
    }
}

