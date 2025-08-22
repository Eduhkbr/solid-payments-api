package com.solid.payments.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

// DTO para receber os dados da requisição
@Data
@Builder
public class PaymentRequest {
    private String paymentMethod;
    private BigDecimal amount;
    private String cardNumber;
    private String cvv;
    private String pixKey;
}
