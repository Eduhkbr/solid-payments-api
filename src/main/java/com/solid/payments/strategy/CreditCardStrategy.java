package com.solid.payments.strategy;

import com.solid.payments.model.Payment;
import com.solid.payments.model.PaymentMethod;
import org.springframework.stereotype.Component;

// Implementação concreta para Cartão de Crédito.
// Fechada para modificação: sua lógica interna é estável.
@Component
public class CreditCardStrategy implements PaymentStrategy {

    @Override
    public void process(Payment payment) {
        // Lógica específica que antes estava no if/else
        System.out.println("Processando pagamento com Cartão de Crédito no valor de " + payment.getAmount());
        // Aqui entraria a lógica de validação de limite, comunicação com gateway, etc.
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }
}