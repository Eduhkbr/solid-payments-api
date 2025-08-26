package com.solid.payments.infrastructure.adapters.out.notification;

import com.solid.payments.domain.port.out.NotificationPort;
import com.solid.payments.model.Payment;
import org.springframework.stereotype.Component;

// Adaptador de Saída: Implementa a porta de notificação.
@Component
public class EmailNotificationAdapter implements NotificationPort {

    @Override
    public void notifyPaymentApproved(Payment payment) {
        System.out.println("ADAPTADOR DE NOTIFICAÇÃO: Enviando e-mail sobre o pagamento " + payment.getId());
    }
}