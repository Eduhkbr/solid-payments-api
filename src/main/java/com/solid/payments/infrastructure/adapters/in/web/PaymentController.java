package com.solid.payments.infrastructure.adapters.in.web;

import com.solid.payments.domain.port.in.ProcessPaymentUseCase;
import com.solid.payments.domain.port.in.ProcessRefundUseCase;
import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Adaptador de Entrada: O Controller agora depende das portas de CASO DE USO.
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final ProcessRefundUseCase processRefundUseCase;

    @PostMapping
    public ResponseEntity<PaymentResponse> process(@RequestBody PaymentRequest request) {
        PaymentResponse response = processPaymentUseCase.processPayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<String> refund(@PathVariable String id) {
        processRefundUseCase.processRefund(id);
        return ResponseEntity.ok("Pedido de estorno para o pagamento " + id + " foi processado.");
    }
}
