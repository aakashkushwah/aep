package com.archexc.acctmgmtservice.controller;

import com.archexc.acctmgmtservice.model.NotificationEvent;
import com.archexc.acctmgmtservice.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    KafkaProducerService producer;

    @PostMapping("/send")
    public String send(@RequestParam String msg) {
        producer.send("test-topic", msg);
        return "Sent!";
    }

    @PostMapping("/send-email-msg")
    public String sendEmailMessage(@RequestBody NotificationEvent event) {
        producer.sendNotificationMessage(event);
        return "SentEmailMessage!";
    }
}

