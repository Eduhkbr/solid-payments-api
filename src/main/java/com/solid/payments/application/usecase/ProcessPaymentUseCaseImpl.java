package com.solid.payments.application.usecase;

import com.solid.payments.domain.port.in.ProcessPaymentUseCase;
import com.solid.payments.domain.port.out.NotificationPort;
import com.solid.payments.domain.port.out.PaymentRepositoryPort;
import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import com.solid.payments.model.*;
import com.solid.payments.strategy.PaymentStrategy;
import com.solid.payments.strategy.PaymentStrategyFactory;
import com.solid.payments.validation.PaymentValidator;
import com.solid.payments.validation.ValidatorFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessPaymentUseCaseImpl implements ProcessPaymentUseCase {

    // A camada de aplicação depende das PORTAS, não das implementações concretas.
    private final PaymentRepositoryPort paymentRepository;
    private final NotificationPort notificationPort;
    private final PaymentStrategyFactory strategyFactory;
    private final ValidatorFactory validatorFactory;

    @Override
    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // ... Lógica de validação e orquestração ...
        PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getPaymentMethod());

        PaymentValidator validator = validatorFactory.getValidator(paymentMethod);
        if (validator != null) {
            validator.validate(request);
        }

        Payment payment = createPaymentInstance(request, paymentMethod);

        PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethod);
        strategy.process(payment);
        payment.setStatus(PaymentStatus.APPROVED);

        paymentRepository.save(payment);
        notificationPort.notifyPaymentApproved(payment);

        return PaymentResponse.from(payment);
    }

    private Payment createPaymentInstance(PaymentRequest request, PaymentMethod paymentMethod) {
        String id = UUID.randomUUID().toString();
        return switch (paymentMethod) {
            case CREDIT_CARD -> new CreditCardPayment(id, request.getAmount(), request.getCardNumber());
            case PIX -> new PixPayment(id, request.getAmount(), request.getPixKey());
            default -> throw new IllegalArgumentException("Método de pagamento não suportado para criação: " + paymentMethod);
        };
    }
}
