package com.solid.payments.validation;

import com.solid.payments.model.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ValidatorFactory {

    private final Map<PaymentMethod, PaymentValidator> validators;

    public ValidatorFactory(List<PaymentValidator> validatorList) {
        this.validators = validatorList.stream()
                .collect(Collectors.toMap(PaymentValidator::getPaymentMethod, Function.identity()));
    }

    public PaymentValidator getValidator(PaymentMethod paymentMethod) {
        return validators.get(paymentMethod);
    }
}