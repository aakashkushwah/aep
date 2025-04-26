package com.archexc.alertservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String orderId;
    private String customerName;
    private String product;
    private int quantity;
    private double price;
    private String status;
}
