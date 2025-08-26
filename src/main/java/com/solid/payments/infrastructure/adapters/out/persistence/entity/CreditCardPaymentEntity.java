package com.solid.payments.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

// Entidade concreta para pagamentos com Cartão de Crédito.
@Entity
@DiscriminatorValue("CREDIT_CARD")
@Data
@EqualsAndHashCode(callSuper = true)
public class CreditCardPaymentEntity extends PaymentEntity {
    private String cardNumber;
}
