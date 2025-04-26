package com.archexc.acctmgmtservice.controller;

import com.archexc.acctmgmtservice.model.ReceiverAccountDTO;
import com.archexc.acctmgmtservice.model.ReceiverStatusChangeRequest;
import com.archexc.acctmgmtservice.service.ReceiverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receiver")
public class ReceiverController {

    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @PostMapping
    public ResponseEntity<ReceiverAccountDTO> createReceiver(@RequestBody ReceiverAccountDTO receiverAccount) {
        ReceiverAccountDTO createdReceiver = receiverService.createReceiver(receiverAccount);
        return ResponseEntity.ok(createdReceiver);
    }

    @GetMapping
    public ResponseEntity<List<ReceiverAccountDTO>> getAllReceivers() {
        List<ReceiverAccountDTO> receivers = receiverService.getAllReceivers();
        return ResponseEntity.ok(receivers);
    }

    @PostMapping("/status-change")
    public ResponseEntity<ReceiverAccountDTO> changeStatus(@RequestBody ReceiverStatusChangeRequest request) {
        ReceiverAccountDTO updatedReceiver = receiverService.statusUpdate(request);
        return ResponseEntity.ok(updatedReceiver);
    }
}
