package com.archexc.acctmgmtservice.model;

import lombok.Data;

@Data
public class ReceiverStatusChangeRequest {

    private Long receiverId;
    private String receiverAccountNumber;
    private String status;
}
