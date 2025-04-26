package com.archexc.acctmgmtservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "receiver_account")
@Data
public class ReceiverAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "bank_account_id", nullable = false)
    private Long bankAccountId;

    @Column(name = "receiver_name", nullable = false, length = 100)
    private String receiverName;

    @Column(name = "receiver_account_number", nullable = false, length = 20)
    private String receiverAccountNumber;

    @Column(name = "receiver_bank_name", nullable = false, length = 100)
    private String receiverBankName;

    @Column(name = "receiver_currency", nullable = false, length = 10)
    private String receiverCurrency;

    @Column(name = "status", nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'active'")
    private String status = "active";

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
