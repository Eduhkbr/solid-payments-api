package com.solid.payments;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import com.solid.payments.service.PaymentOrchestratorService;
import com.solid.payments.service.PaymentService;
import com.solid.payments.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Responsabilidade Única: Lidar com requisições HTTP para pagamentos
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    // A dependência agora é do orquestrador
    private final PaymentOrchestratorService paymentOrchestratorService;
    private final RefundService refundService;

    @PostMapping
    public ResponseEntity<PaymentResponse> process(@RequestBody PaymentRequest request) {
        // O controller chama o orquestrador, que cuida de todo o fluxo.
        PaymentResponse response = paymentOrchestratorService.processPayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/refund")
    public ResponseEntity<String> refund(@PathVariable String id) {
        refundService.processRefund(id);
        return ResponseEntity.ok("Pedido de estorno para o pagamento " + id + " foi processado.");
    }
}