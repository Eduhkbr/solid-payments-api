package com.solid.payments.service;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import com.solid.payments.model.*;
import com.solid.payments.repository.PaymentRepository;
import com.solid.payments.validation.PaymentValidator;
import com.solid.payments.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

// O antigo PaymentService agora é um orquestrador.
// Sua função é coordenar os diferentes componentes (validadores, processadores, etc.).
@Service
@RequiredArgsConstructor
public class PaymentOrchestratorService {

    private final PaymentRepository paymentRepository;
    private final NotificationService notificationService;
    private final PaymentProcessorService paymentProcessor; // Depende do novo serviço focado
    private final ValidatorFactory validatorFactory; // Depende da nova Factory de validadores

    public PaymentResponse processPayment(PaymentRequest request) {
        // 1. Validação genérica
        if (request.getAmount() == null || request.getAmount().signum() <= 0) {
            throw new IllegalArgumentException("Valor do pagamento é inválido.");
        }
        PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getPaymentMethod());

        // 2. Validação específica (usando a Factory de validadores)
        PaymentValidator validator = validatorFactory.getValidator(paymentMethod);
        if (validator != null) {
            validator.validate(request);
        }

        // 3. Criação da entidade
        Payment payment = createPaymentInstance(request, paymentMethod);

        // 4. Delega o processamento para o serviço específico
        paymentProcessor.process(payment);
        payment.setStatus(PaymentStatus.APPROVED);

        // 5. Persistência
        paymentRepository.save(payment);

        // 6. Notificação
        notificationService.notifyPaymentApproved(payment);

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
