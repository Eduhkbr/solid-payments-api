package com.solid.payments.infrastructure.adapters.out.persistence.entity;

import com.solid.payments.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAYMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class PaymentEntity {
    @Id
    protected String id;
    @Column(nullable = false)
    protected BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected PaymentStatus status;
    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;
}
