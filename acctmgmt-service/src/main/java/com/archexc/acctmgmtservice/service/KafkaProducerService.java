package com.archexc.acctmgmtservice.service;

import com.archexc.acctmgmtservice.model.AccountEvent;
import com.archexc.acctmgmtservice.model.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, NotificationEvent> kafkaTemplateNotification;

    @Autowired
    private KafkaTemplate<String, AccountEvent> kafkaTemplateAlert;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendEmailMessage(String topic, NotificationEvent event) {
        kafkaTemplateNotification.send(topic, event);
    }

    public void sendAlertMessage(String topic, AccountEvent event) {
        kafkaTemplateAlert.send(topic, event);
    }
}
