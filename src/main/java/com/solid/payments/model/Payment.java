package com.solid.payments.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Payment {
    private String id;
    private String paymentMethod;
    private BigDecimal amount;
    private PaymentStatus status;
}