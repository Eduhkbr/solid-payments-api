package com.hexagonal.payments.dto;

import com.hexagonal.payments.model.Pagamento;
import com.hexagonal.payments.model.StatusPagamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoResponse {
    private String id;
    private String tipo;
    private BigDecimal valor;
    private StatusPagamento status;

    public static PagamentoResponse from(Pagamento pagamento) {
        return PagamentoResponse.builder()
                .id(pagamento.getId())
                .tipo(pagamento.getTipo())
                .valor(pagamento.getValor())
                .status(pagamento.getStatus())
                .build();
    }
}