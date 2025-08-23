package com.solid.payments.repository;

import com.solid.payments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Agora usamos o Spring Data JPA.
// Ele entende a hierarquia de classes de Payment.
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
}
