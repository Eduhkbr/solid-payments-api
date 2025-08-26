package com.solid.payments.infrastructure.adapters.out.persistence;

import com.solid.payments.infrastructure.adapters.out.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Esta é a interface real do Spring Data JPA que interage com o banco de dados.
@Repository
public interface SpringDataPaymentRepository extends JpaRepository<PaymentEntity, String> {
}
