package com.solid.payments.repository;

import com.solid.payments.model.Payment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PaymentRepository {

    public void save(Payment payment) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
            String sql = "INSERT INTO PAYMENTS (ID, PAYMENT_METHOD, AMOUNT, STATUS) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, payment.getId());
            stmt.setString(2, payment.getPaymentMethod());
            stmt.setBigDecimal(3, payment.getAmount());
            stmt.setString(4, payment.getStatus().name());
            stmt.executeUpdate();
            System.out.println("Pagamento salvo no banco de dados com ID: " + payment.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pagamento no banco de dados", e);
        }
    }
}
