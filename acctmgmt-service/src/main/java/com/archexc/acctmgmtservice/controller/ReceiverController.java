package com.archexc.acctmgmtservice.controller;

import com.archexc.acctmgmtservice.entity.BankAccount;
import com.archexc.acctmgmtservice.entity.ReceiverAccount;
import com.archexc.acctmgmtservice.model.ReceiverStatusChangeRequest;
import com.archexc.acctmgmtservice.service.ReceiverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receiver")
public class ReceiverController {

    private ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @PostMapping
    public ResponseEntity<ReceiverAccount> createReceiver(@RequestBody ReceiverAccount receiverAccount) {
        ReceiverAccount createdReceiver = receiverService.createReceiver(receiverAccount);
        return ResponseEntity.ok(createdReceiver);
    }

    @GetMapping
    public ResponseEntity<List<ReceiverAccount>> getAllReceivers() {
        List<ReceiverAccount> receivers = receiverService.getAllReceivers();
        return ResponseEntity.ok(receivers);
    }

    @PostMapping("/status-change")
    public ResponseEntity<ReceiverAccount> changeStatus(@RequestBody ReceiverStatusChangeRequest request) {
        ReceiverAccount updatedReceiver = receiverService.statusUpdate(request);
        return ResponseEntity.ok(updatedReceiver);
    }
}
