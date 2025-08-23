package com.solid.payments.validation;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.model.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentValidator implements PaymentValidator {

    @Override
    public void validate(PaymentRequest request) {
        if (request.getCardNumber() == null || !request.getCardNumber().matches("\\d{16}")) {
            throw new ValidationException("Número do cartão de crédito é inválido.");
        }
        if (request.getCvv() == null || !request.getCvv().matches("\\d{3}")) {
            throw new ValidationException("CVV do cartão de crédito é inválido.");
        }
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }
}
