package com.solid.payments.service;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import com.solid.payments.model.*;
import com.solid.payments.repository.PaymentRepository;
import com.solid.payments.strategy.PaymentStrategy;
import com.solid.payments.strategy.PaymentStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationService notificationService;
    private final PaymentStrategyFactory strategyFactory;

    public PaymentResponse process(PaymentRequest request) {
        // ... validações ...

        PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getPaymentMethod());

        // Cria a instância da subclasse correta
        Payment payment = createPaymentInstance(request, paymentMethod);

        PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethod);
        strategy.process(payment);

        payment.setStatus(PaymentStatus.APPROVED);

        paymentRepository.save(payment);
        notificationService.notifyPaymentApproved(payment);

        return PaymentResponse.from(payment);
    }

    // Método auxiliar para criar a instância correta
    private Payment createPaymentInstance(PaymentRequest request, PaymentMethod paymentMethod) {
        String id = UUID.randomUUID().toString();
        return switch (paymentMethod) {
            case CREDIT_CARD -> new CreditCardPayment(id, request.getAmount(), request.getCardNumber());
            case PIX -> new PixPayment(id, request.getAmount(), request.getPixKey());
            default -> throw new IllegalArgumentException("Método de pagamento não suportado para criação: " + paymentMethod);
        };
    }
}