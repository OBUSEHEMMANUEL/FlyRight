package com.example.flyright.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String name;
    private String description;
    private BigDecimal amount;
}
