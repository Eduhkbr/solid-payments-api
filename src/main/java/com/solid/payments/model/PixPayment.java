package com.solid.payments.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// Subclasse concreta para PIX.
// NÃO implementa Refundable, respeitando o LSP.
@Entity
@DiscriminatorValue("PIX")
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
