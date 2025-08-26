package com.solid.payments.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

// Entidade concreta para pagamentos com PIX.
@Entity
@DiscriminatorValue("PIX")
@Data
@EqualsAndHashCode(callSuper = true)
public class PixPaymentEntity extends PaymentEntity {
    private String pixKey;
}