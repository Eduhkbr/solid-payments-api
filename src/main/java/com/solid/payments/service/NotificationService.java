package com.solid.payments.service;

import com.solid.payments.model.Payment;
import org.springframework.stereotype.Service;

// Responsabilidade Única: Notificar usuários
@Service
public class NotificationService {

    public void notifyPaymentApproved(Payment payment) {
        // Lógica para enviar e-mail, SMS, push notification, etc.
        System.out.println("Enviando e-mail de confirmação para o cliente sobre o pagamento " + payment.getId());
    }
}
