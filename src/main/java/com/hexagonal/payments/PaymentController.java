package com.hexagonal.payments;

import com.hexagonal.payments.dto.PaymentRequest;
import com.hexagonal.payments.dto.PaymentResponse;
import com.hexagonal.payments.service.PaymentService;
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
    @PostMapping("/{id}/estorno")
    public ResponseEntity<String> refund(@PathVariable String id) {
        System.out.println("Estornando pagamento com ID: " + id);
        return ResponseEntity.ok("Pagamento estornado com sucesso.");
    }
}