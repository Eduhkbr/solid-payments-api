package com.solid.payments.service;

import com.solid.payments.model.Payment;
import com.solid.payments.model.Refundable;
import com.solid.payments.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final PaymentRepository paymentRepository;

    public void processRefund(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + paymentId));

        // LSP em ação: Verificamos se o objeto TEM a capacidade de ser estornado.
        // Não precisamos de um "if" para cada tipo (PIX, Boleto, etc.).
        if (!(payment instanceof Refundable refundablePayment)) {
            throw new UnsupportedOperationException("Este tipo de pagamento não pode ser estornado: " + payment.getPaymentMethod());
        }

        if (!refundablePayment.canBeRefunded()) {
            throw new IllegalStateException("Pagamento não atende aos critérios para estorno.");
        }

        refundablePayment.processRefund();

        // Salva o estado atualizado do pagamento (ex: status = REFUNDED)
        paymentRepository.save((Payment) refundablePayment);
        System.out.println("Estorno para o pagamento " + paymentId + " concluído e salvo.");
    }
}
