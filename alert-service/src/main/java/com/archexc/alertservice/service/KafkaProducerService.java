package com.archexc.alertservice.service;


import com.archexc.alertservice.model.AccountEvent;
import com.archexc.alertservice.model.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {


    @Autowired
    private KafkaTemplate<String, NotificationEvent> kafkaTemplateNotification;

    @Autowired
    private KafkaTemplate<String, AccountEvent> kafkaTemplateAlert;

    public void sendNotificationMessage(NotificationEvent event) {
        kafkaTemplateNotification.send("notification-topic", event);
    }

    public void sendAccountMessage(AccountEvent event) {
        kafkaTemplateAlert.send("account-topic", event);
    }
}
