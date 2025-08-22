package com.solid.payments.service;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import com.solid.payments.model.Payment;
import com.solid.payments.model.PaymentStatus;
import com.solid.payments.model.PaymentMethod;
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
        if (request.getAmount() == null || request.getAmount().signum() <= 0) {
            throw new IllegalArgumentException("Valor do pagamento é inválido.");
        }

        PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getPaymentMethod());

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .status(PaymentStatus.PROCESSING)
                .build();

        // O bloco if/else foi substituído por esta única linha!
        // O serviço agora está FECHADO PARA MODIFICAÇÃO.
        PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethod);
        strategy.process(payment);

        payment.setStatus(PaymentStatus.APPROVED);

        paymentRepository.save(payment);
        notificationService.notifyPaymentApproved(payment);

        return PaymentResponse.from(payment);
    }
}
