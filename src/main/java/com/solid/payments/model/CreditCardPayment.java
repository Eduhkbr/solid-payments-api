package com.solid.payments.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CreditCardPayment extends Payment implements Refundable {

    private String cardNumber;

    public CreditCardPayment(String id, BigDecimal amount, String cardNumber) {
        super(id, amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processRefund() {
        // Lógica de estorno específica para cartão de crédito
        System.out.println("Processando estorno para o cartão de crédito. ID: " + this.id);
        this.setStatus(PaymentStatus.REFUNDED);
    }

    @Override
    public boolean canBeRefunded() {
        // Regra de negócio: só pode estornar em até 90 dias.
        long daysSincePayment = ChronoUnit.DAYS.between(this.getCreatedAt(), LocalDateTime.now());
        return this.getStatus() == PaymentStatus.APPROVED && daysSincePayment <= 90;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }
}
