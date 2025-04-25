package com.archexc.acctmgmtservice.controller;

import com.archexc.acctmgmtservice.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

