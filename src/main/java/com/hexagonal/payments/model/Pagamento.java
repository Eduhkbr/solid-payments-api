package com.hexagonal.payments.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Pagamento {
    private String id;
    private String tipo;
    private BigDecimal valor;
    private StatusPagamento status;
}