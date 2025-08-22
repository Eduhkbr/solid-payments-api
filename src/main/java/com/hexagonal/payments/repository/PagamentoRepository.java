package com.hexagonal.payments.repository;

import com.hexagonal.payments.model.Pagamento;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PagamentoRepository {

    public void salvar(Pagamento pagamento) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            String sql = "INSERT INTO PAGAMENTOS (ID, TIPO, VALOR, STATUS) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pagamento.getId());
            stmt.setString(2, pagamento.getTipo());
            stmt.setBigDecimal(3, pagamento.getValor());
            stmt.setString(4, pagamento.getStatus().name());
            stmt.executeUpdate();
            System.out.println("Pagamento salvo no banco de dados com ID: " + pagamento.getId());
        } catch (SQLException e) {
            // Em um app real, teríamos um tratamento de exceção mais robusto
            throw new RuntimeException("Erro ao salvar pagamento no banco de dados", e);
        }
    }
}
