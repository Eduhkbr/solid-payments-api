package com.solid.payments.validation;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.model.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class PixPaymentValidator implements PaymentValidator {

    @Override
    public void validate(PaymentRequest request) {
        if (request.getPixKey() == null || request.getPixKey().isBlank()) {
            throw new ValidationException("A chave PIX é obrigatória.");
        }
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.PIX;
    }
}