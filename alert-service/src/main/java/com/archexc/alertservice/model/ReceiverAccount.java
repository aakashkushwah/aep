package com.archexc.alertservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverAccount {
    private String receiverName;
    private String receiverAccountNumber;
    private String receiverBankName;
    private String receiverCurrency;
    private String status;
    private int balance;
}
