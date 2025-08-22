package com.solid.payments.strategy;

import com.solid.payments.model.Payment;
import com.solid.payments.model.PaymentMethod;

// A interface define o contrato para todas as estratégias de pagamento.
// Aberta para extensão: podemos criar novas implementações.
public interface PaymentStrategy {
    void process(Payment payment);
    PaymentMethod getPaymentMethod();
}
