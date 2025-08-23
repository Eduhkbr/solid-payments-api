package com.hexagonal.payments.dto;

import com.hexagonal.payments.model.Payment;
import com.hexagonal.payments.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

// DTO para enviar os dados na resposta
@Data
@Builder
public class PaymentResponse {
    private String id;
    private String paymentMethod;
    private BigDecimal amount;
    private PaymentStatus status;

    public static PaymentResponse from(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }
}