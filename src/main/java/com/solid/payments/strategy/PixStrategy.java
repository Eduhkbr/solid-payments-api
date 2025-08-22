package com.solid.payments.strategy;

import com.solid.payments.model.Payment;
import com.solid.payments.model.PaymentMethod;
import org.springframework.stereotype.Component;

// Implementação concreta para PIX.
@Component
public class PixStrategy implements PaymentStrategy {

    @Override
    public void process(Payment payment) {
        // Lógica específica que antes estava no if/else
        System.out.println("Processando pagamento com PIX no valor de " + payment.getAmount());
        // Aqui entraria a lógica de geração de QR Code, etc.
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.PIX;
    }
}