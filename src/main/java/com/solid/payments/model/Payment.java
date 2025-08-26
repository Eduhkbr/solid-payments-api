package com.solid.payments.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class Payment {

    protected String id;
    protected BigDecimal amount;
    protected PaymentStatus status;
    protected LocalDateTime createdAt;

    public Payment(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.PROCESSING;
        this.createdAt = LocalDateTime.now();
    }

    public abstract PaymentMethod getPaymentMethod();
}
