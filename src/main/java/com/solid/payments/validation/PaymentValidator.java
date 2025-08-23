package com.solid.payments.validation;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.model.PaymentMethod;

// Interface específica para validação
// (Interface Segregation Principle)
public interface PaymentValidator {
    void validate(PaymentRequest paymentRequest);
    PaymentMethod getPaymentMethod();
}
