package com.solid.payments.domain.port.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String paymentId) {
        super("Pagamento não encontrado com ID: " + paymentId);
    }
}
