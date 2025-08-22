package com.hexagonal.payments;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @PostMapping
    public ResponseEntity<String> processar(@RequestBody Map<String, Object> request) {
        try {
            // Extração de dados do request
            String tipo = (String) request.get("tipo");
            BigDecimal valor = new BigDecimal(request.get("valor").toString());

            // VIOLAÇÃO DE TODOS OS PRINCÍPIPIOS SOLID!

            // 1. Validação misturada (Violação SRP)
            if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest().body("Valor do pagamento é inválido.");
            }
            if (tipo == null || tipo.isBlank()) {
                return ResponseEntity.badRequest().body("Tipo de pagamento é obrigatório.");
            }

            // 2. Lógica de negócio no controller (Violação SRP e OCP)
            if (tipo.equals("CARTAO_CREDITO")) {
                String numeroCartao = (String) request.get("numeroCartao");
                String cvv = (String) request.get("cvv");

                // Validação de cartão inline
                if (numeroCartao == null || numeroCartao.length() != 16) {
                    return ResponseEntity.badRequest().body("Número do cartão inválido.");
                }
                if (cvv == null || cvv.length() != 3) {
                    return ResponseEntity.badRequest().body("CVV do cartão inválido.");
                }

                System.out.println("Validando limite do cartão...");
                System.out.println("Processando pagamento com Cartão de Crédito no valor de " + valor);

                // 3. Acoplamento com implementação concreta de HTTP Client (Violação DIP)
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.gateway.com/charge")) // Gateway Fictício
                        .header("Authorization", "Bearer sk_test_12345")
                        .POST(HttpRequest.BodyPublishers.ofString(
                                "{\"amount\":" + valor + ",\"card\":\"" + numeroCartao + "\"}"
                        ))
                        .build();

                // Simulação de resposta
                // HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                // if(response.statusCode() != 200) {
                //    return ResponseEntity.status(500).body("Erro no gateway de pagamento");
                // }
                System.out.println("Pagamento com cartão aprovado no gateway.");


            } else if (tipo.equals("PIX")) {
                String chavePix = (String) request.get("chavePix");
                if (chavePix == null || chavePix.isBlank()) {
                    return ResponseEntity.badRequest().body("Chave PIX é obrigatória.");
                }
                System.out.println("Processando pagamento com PIX no valor de " + valor);
                System.out.println("Gerando QR Code para a chave: " + chavePix);

            } else if (tipo.equals("BOLETO")) {
                System.out.println("Processando pagamento com Boleto no valor de " + valor);
                System.out.println("Gerando linha digitável e enviando para o e-mail do cliente.");
            } else {
                return ResponseEntity.badRequest().body("Tipo de pagamento não suportado.");
            }

            // 4. Persistência de dados direto no controller (Violação SRP)
            String pagamentoId = UUID.randomUUID().toString();
            try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
                String sql = "INSERT INTO PAGAMENTOS (ID, TIPO, VALOR, STATUS) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, pagamentoId);
                stmt.setString(2, tipo);
                stmt.setBigDecimal(3, valor);
                stmt.setString(4, "APROVADO");
                stmt.executeUpdate();
                System.out.println("Pagamento salvo no banco de dados com ID: " + pagamentoId);
            }

            // 5. Notificação por e-mail hardcoded (Violação SRP)
            System.out.println("Enviando e-mail de confirmação para o cliente...");

            return ResponseEntity.ok("Pagamento processado com sucesso! ID: " + pagamentoId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno no processamento do pagamento.");
        }
    }

    // Violação LSP e ISP: Um endpoint de estorno que não se aplica a todos os tipos de pagamento
    @PostMapping("/{id}/estorno")
    public ResponseEntity<String> estornar(@PathVariable String id) {
        // Lógica de estorno...
        // Problema: PIX e Boleto não podem ser estornados da mesma forma que um Cartão.
        // O código aqui teria que fazer um "if" no tipo de pagamento para decidir o que fazer,
        // o que é um sintoma de violação do LSP.
        System.out.println("Estornando pagamento com ID: " + id);
        return ResponseEntity.ok("Pagamento estornado com sucesso.");
    }
}