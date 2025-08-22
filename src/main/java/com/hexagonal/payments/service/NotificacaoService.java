package com.hexagonal.payments.service;

import com.hexagonal.payments.model.Pagamento;
import org.springframework.stereotype.Service;

// Responsabilidade Única: Notificar usuários
@Service
public class NotificacaoService {

    public void notificarPagamentoAprovado(Pagamento pagamento) {
        // Lógica para enviar e-mail, SMS, push notification, etc.
        System.out.println("Enviando e-mail de confirmação para o cliente sobre o pagamento " + pagamento.getId());
        System.out.println("Valor: " + pagamento.getValor());
    }
}
