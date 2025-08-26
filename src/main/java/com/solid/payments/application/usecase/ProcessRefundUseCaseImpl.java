package com.solid.payments.application.usecase;

import com.solid.payments.domain.port.in.ProcessRefundUseCase;
import com.solid.payments.domain.port.out.PaymentRepositoryPort;
import com.solid.payments.model.Payment;
import com.solid.payments.model.Refundable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Implementação do caso de uso de estorno.
@Service
@RequiredArgsConstructor
public class ProcessRefundUseCaseImpl implements ProcessRefundUseCase {

    private final PaymentRepositoryPort paymentRepository;

    @Override
    @Transactional
    public void processRefund(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + paymentId));

        if (!(payment instanceof Refundable refundablePayment)) {
            throw new UnsupportedOperationException("Este tipo de pagamento não pode ser estornado: " + payment.getPaymentMethod());
        }

        if (!refundablePayment.canBeRefunded()) {
            throw new IllegalStateException("Pagamento não atende aos critérios para estorno.");
        }

        refundablePayment.processRefund();

        paymentRepository.save((Payment) refundablePayment);
    }
}
