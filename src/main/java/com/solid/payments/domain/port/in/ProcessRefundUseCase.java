package com.solid.payments.domain.port.in;

// Porta de Entrada: define o caso de uso para processar um estorno.
public interface ProcessRefundUseCase {
    void processRefund(String paymentId);
}