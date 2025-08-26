package com.solid.payments.domain.port.out;

import com.solid.payments.model.Payment;

import java.util.Optional;

// Porta de Saída: define o contrato para persistência de pagamentos.
public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(String id);
}
