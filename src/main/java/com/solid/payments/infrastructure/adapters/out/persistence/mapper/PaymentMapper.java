package com.solid.payments.infrastructure.adapters.out.persistence.mapper;

import com.solid.payments.infrastructure.adapters.out.persistence.entity.CreditCardPaymentEntity;
import com.solid.payments.infrastructure.adapters.out.persistence.entity.PaymentEntity;
import com.solid.payments.infrastructure.adapters.out.persistence.entity.PixPaymentEntity;
import com.solid.payments.model.CreditCardPayment;
import com.solid.payments.model.Payment;
import com.solid.payments.model.PixPayment;
import org.springframework.stereotype.Component;

// Mapper manual para converter entre o modelo de domínio e as entidades de persistência.
// Em um projeto maior, poderíamos usar uma biblioteca como MapStruct.
@Component
public class PaymentMapper {

    public PaymentEntity toEntity(Payment payment) {
        if (payment instanceof CreditCardPayment creditCardPayment) {
            CreditCardPaymentEntity entity = new CreditCardPaymentEntity();
            entity.setId(creditCardPayment.getId());
            entity.setAmount(creditCardPayment.getAmount());
            entity.setStatus(creditCardPayment.getStatus());
            entity.setCreatedAt(creditCardPayment.getCreatedAt());
            entity.setCardNumber(creditCardPayment.getCardNumber());
            return entity;
        }
        if (payment instanceof PixPayment pixPayment) {
            PixPaymentEntity entity = new PixPaymentEntity();
            entity.setId(pixPayment.getId());
            entity.setAmount(pixPayment.getAmount());
            entity.setStatus(pixPayment.getStatus());
            entity.setCreatedAt(pixPayment.getCreatedAt());
            entity.setPixKey(pixPayment.getPixKey());
            return entity;
        }
        throw new IllegalArgumentException("Tipo de pagamento desconhecido para mapeamento: " + payment.getClass());
    }

    public Payment toDomain(PaymentEntity entity) {
        if (entity instanceof CreditCardPaymentEntity creditCardEntity) {
            CreditCardPayment domain = new CreditCardPayment();
            domain.setId(creditCardEntity.getId());
            domain.setAmount(creditCardEntity.getAmount());
            domain.setStatus(creditCardEntity.getStatus());
            domain.setCreatedAt(creditCardEntity.getCreatedAt());
            domain.setCardNumber(creditCardEntity.getCardNumber());
            return domain;
        }
        if (entity instanceof PixPaymentEntity pixEntity) {
            PixPayment domain = new PixPayment();
            domain.setId(pixEntity.getId());
            domain.setAmount(pixEntity.getAmount());
            domain.setStatus(pixEntity.getStatus());
            domain.setCreatedAt(pixEntity.getCreatedAt());
            domain.setPixKey(pixEntity.getPixKey());
            return domain;
        }
        throw new IllegalArgumentException("Tipo de entidade desconhecida para mapeamento: " + entity.getClass());
    }
}
