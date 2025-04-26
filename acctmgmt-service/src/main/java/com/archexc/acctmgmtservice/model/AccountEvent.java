package com.archexc.acctmgmtservice.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountEvent {

    private Long bankAccountId;
    private String receiverName;
    private String receiverAccountNumber;
    private String receiverBankName;
    private String receiverCurrency;
    private String status;
}
