package com.solid.payments;

import com.solid.payments.dto.PaymentRequest;
import com.solid.payments.dto.PaymentResponse;
import com.solid.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Responsabilidade Única: Lidar com requisições HTTP para pagamentos
@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> process(@RequestBody PaymentRequest request) {
        // Delega toda a lógica de negócio para o serviço
        PaymentResponse response = paymentService.process(request);
        return ResponseEntity.ok(response);
    }

    // O endpoint de estorno continua aqui, mas sua lógica será movida
    // para um serviço apropriado nas próximas etapas.
    @PostMapping("/{id}/refund")
    public ResponseEntity<String> refund(@PathVariable String id) {
        System.out.println("Processando estorno para o pagamento com ID: " + id);
        return ResponseEntity.ok("Estorno processado com sucesso.");
    }
}