package com.archexc.acctmgmtservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEvent {

    private Long bankAccountId;
    private String receiverName;
    private String receiverAccountNumber;
    private String receiverBankName;
    private String receiverCurrency;
    private String status;
}
