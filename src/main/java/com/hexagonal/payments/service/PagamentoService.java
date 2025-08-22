package com.hexagonal.payments.service;

import com.hexagonal.payments.dto.PagamentoRequest;
import com.hexagonal.payments.dto.PagamentoResponse;
import com.hexagonal.payments.model.Pagamento;
import com.hexagonal.payments.model.StatusPagamento;
import com.hexagonal.payments.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Responsabilidade Única: Orquestrar a lógica de negócio de pagamento
@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final NotificacaoService notificacaoService;

    public PagamentoResponse processar(PagamentoRequest request) {
        // A validação ainda está aqui, mas já é um avanço.
        if (request.getValor() == null || request.getValor().signum() <= 0) {
            throw new IllegalArgumentException("Valor do pagamento é inválido.");
        }

        Pagamento pagamento = Pagamento.builder()
                .id(UUID.randomUUID().toString())
                .tipo(request.getTipo())
                .valor(request.getValor())
                .status(StatusPagamento.PROCESSANDO)
                .build();

        if ("CARTAO_CREDITO".equals(pagamento.getTipo())) {
            System.out.println("Processando pagamento com Cartão de Crédito no valor de " + pagamento.getValor());
            // Lógica de gateway de cartão...
        } else if ("PIX".equals(pagamento.getTipo())) {
            System.out.println("Processando pagamento com PIX no valor de " + pagamento.getValor());
            // Lógica de geração de QR Code...
        } else {
            throw new IllegalArgumentException("Tipo de pagamento não suportado.");
        }

        pagamento.setStatus(StatusPagamento.APROVADO);
        // Simulação de sucesso

        // Delega a persistência para o repositório
        pagamentoRepository.salvar(pagamento);

        // Delega a notificação para o serviço de notificação
        notificacaoService.notificarPagamentoAprovado(pagamento);

        return PagamentoResponse.from(pagamento);
    }
}
