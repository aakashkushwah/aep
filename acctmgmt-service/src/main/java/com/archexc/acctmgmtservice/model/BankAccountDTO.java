package com.archexc.acctmgmtservice.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
    private Long accountId;
    
    private String accountNumber;

    private Long customerId;

    private String accountType;

    private Double balance;

    private String currency;

    private LocalDateTime createdAt;
}
