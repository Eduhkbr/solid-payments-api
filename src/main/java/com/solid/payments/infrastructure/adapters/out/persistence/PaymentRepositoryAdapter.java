package com.solid.payments.infrastructure.adapters.out.persistence;

import com.solid.payments.domain.port.out.PaymentRepositoryPort;
import com.solid.payments.infrastructure.adapters.out.persistence.entity.PaymentEntity;
import com.solid.payments.infrastructure.adapters.out.persistence.mapper.PaymentMapper;
import com.solid.payments.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Adaptador de Saída: Implementa a porta de persistência usando Spring Data JPA.
@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final SpringDataPaymentRepository jpaRepository;
    private final PaymentMapper mapper;

    @Override
    public Payment save(Payment payment) {
        // Mapeia do modelo de domínio para a entidade JPA
        PaymentEntity entity = mapper.toEntity(payment);
        // Salva a entidade usando o repositório do Spring Data
        PaymentEntity savedEntity = jpaRepository.save(entity);
        // Mapeia de volta da entidade para o modelo de domínio para retornar
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Payment> findById(String id) {
        // Busca a entidade pelo ID e mapeia para o modelo de domínio se encontrar
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}