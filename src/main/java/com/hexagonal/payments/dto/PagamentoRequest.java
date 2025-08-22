package com.hexagonal.payments.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoRequest {
    private String tipo;
    private BigDecimal valor;
    private String numeroCartao;
    private String cvv;
    private String chavePix;
}
