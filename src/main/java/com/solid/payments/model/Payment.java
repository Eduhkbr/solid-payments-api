package com.solid.payments.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// A classe base agora é abstrata e uma entidade JPA.
@Entity
@Table(name = "PAYMENTS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Estratégia de herança
@DiscriminatorColumn(name = "PAYMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public abstract class Payment {

    @Id
    protected String id;

    @Column(nullable = false)
    protected BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected PaymentStatus status;

    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;

    public Payment(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.PROCESSING;
        this.createdAt = LocalDateTime.now();
    }

    public abstract PaymentMethod getPaymentMethod();
}
