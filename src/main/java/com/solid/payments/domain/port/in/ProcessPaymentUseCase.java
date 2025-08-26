package com.solid.payments.domain.port.in;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;

// Porta de Entrada: define o caso de uso para processar um pagamento.
public interface ProcessPaymentUseCase {
    PaymentResponse processPayment(PaymentRequest request);
}
