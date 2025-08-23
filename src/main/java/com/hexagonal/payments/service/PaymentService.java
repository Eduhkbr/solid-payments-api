package com.hexagonal.payments.service;

import com.hexagonal.payments.dto.PaymentRequest;
import com.hexagonal.payments.dto.PaymentResponse;
import com.hexagonal.payments.model.Payment;
import com.hexagonal.payments.model.PaymentStatus;
import com.hexagonal.payments.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Responsabilidade Única: Orquestrar a lógica de negócio de pagamento
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final NotificationService notificationService;

    public PaymentResponse process(PaymentRequest request) {
        // A validação ainda está aqui, mas já é um avanço.
        if (request.getAmount() == null || request.getAmount().signum() <= 0) {
            throw new IllegalArgumentException("Valor do pagamento é inválido.");
        }

        Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .status(PaymentStatus.PROCESSING)
                .build();

        if ("CARTAO_CREDITO".equals(payment.getPaymentMethod())) {
            System.out.println("Processando pagamento com Cartão de Crédito no valor de " + payment.getAmount());
            // Lógica de gateway de cartão...
        } else if ("PIX".equals(payment.getPaymentMethod())) {
            System.out.println("Processando pagamento com PIX no valor de " + payment.getAmount());
            // Lógica de geração de QR Code...
        } else {
            throw new IllegalArgumentException("Tipo de pagamento não suportado.");
        }

        payment.setStatus(PaymentStatus.APPROVED);
        // Simulação de sucesso

        // Delega a persistência para o repositório
        paymentRepository.save(payment);

        // Delega a notificação para o serviço de notificação
        notificationService.notifyPaymentApproved(payment);

        return PaymentResponse.from(payment);
    }
}
