package com.solid.payments.dto;

import com.solid.payments.model.Payment;
import com.solid.payments.model.PaymentMethod;
import com.solid.payments.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

// DTO para enviar os dados na resposta
@Data
@Builder
public class PaymentResponse {
    private String id;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private PaymentStatus status;

    public static PaymentResponse from(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                // Agora chama o metodo abstrato
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }
}