package com.solid.payments.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PixPayment extends Payment {

    private String pixKey;

    public PixPayment(String id, BigDecimal amount, String pixKey) {
        super(id, amount);
        this.pixKey = pixKey;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.PIX;
    }
}
