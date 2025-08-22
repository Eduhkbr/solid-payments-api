package com.hexagonal.payments;

import com.hexagonal.payments.dto.PagamentoRequest;
import com.hexagonal.payments.dto.PagamentoResponse;
import com.hexagonal.payments.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Responsabilidade Única: Lidar com requisições HTTP para pagamentos
@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponse> processar(@RequestBody PagamentoRequest request) {
        // Delega toda a lógica de negócio para o serviço
        PagamentoResponse response = pagamentoService.processar(request);
        return ResponseEntity.ok(response);
    }

    // O endpoint de estorno continua aqui, mas sua lógica será movida
    // para um serviço apropriado nas próximas etapas.
    @PostMapping("/{id}/estorno")
    public ResponseEntity<String> estornar(@PathVariable String id) {
        System.out.println("Estornando pagamento com ID: " + id);
        return ResponseEntity.ok("Pagamento estornado com sucesso.");
    }
}