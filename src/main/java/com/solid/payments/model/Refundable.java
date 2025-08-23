package com.solid.payments.model;

import java.time.LocalDateTime;

// Interface que define a "capacidade" de um pagamento ser estornável.
// Segrega a interface de acordo com o ISP.
public interface Refundable {
    void processRefund();

    boolean canBeRefunded();

    LocalDateTime getCreatedAt();
}
