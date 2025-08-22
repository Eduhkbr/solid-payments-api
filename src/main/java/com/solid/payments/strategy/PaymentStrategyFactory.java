package com.solid.payments.strategy;

import com.solid.payments.model.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentStrategyFactory {

    private final Map<PaymentMethod, PaymentStrategy> strategies;

    // O Spring injeta uma lista de todas as classes que implementam EstrategiaPagamento
    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(PaymentStrategy::getPaymentMethod, Function.identity()));
    }

    public PaymentStrategy getStrategy(PaymentMethod paymentMethod) {
        PaymentStrategy strategy = strategies.get(paymentMethod);
        if (strategy == null) {
            throw new IllegalArgumentException("Método de pagamento não suportado: " + paymentMethod);
        }
        return strategy;
    }
}

