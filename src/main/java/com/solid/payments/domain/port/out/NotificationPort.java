package com.solid.payments.domain.port.out;

import com.solid.payments.model.Payment;

// Porta de Saída: define o contrato para enviar notificações.
public interface NotificationPort {
    void notifyPaymentApproved(Payment payment);
}