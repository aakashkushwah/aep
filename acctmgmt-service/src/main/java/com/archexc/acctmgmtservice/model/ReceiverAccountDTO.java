package com.archexc.acctmgmtservice.model;

import lombok.Data;

@Data
public class ReceiverAccountDTO {
    private Long receiverId;
    private Long bankAccountId;
    private String receiverName;
    private String receiverAccountNumber;
    private String receiverBankName;
    private String receiverCurrency;
    private String status;
}
