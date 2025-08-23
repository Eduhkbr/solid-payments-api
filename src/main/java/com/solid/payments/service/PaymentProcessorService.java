package com.solid.payments.service;

import com.solid.payments.model.Payment;
import com.solid.payments.strategy.PaymentStrategy;
import com.solid.payments.strategy.PaymentStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Este serviço tem uma única e específica responsabilidade: processar o pagamento.
// Ele depende de uma interface focada (PaymentStrategyFactory).
@Service
@RequiredArgsConstructor
public class PaymentProcessorService {

    private final PaymentStrategyFactory strategyFactory;

    public void process(Payment payment) {
        PaymentStrategy strategy = strategyFactory.getStrategy(payment.getPaymentMethod());
        strategy.process(payment);
    }
}