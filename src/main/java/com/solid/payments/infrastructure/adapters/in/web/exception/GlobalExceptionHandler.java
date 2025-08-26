package com.solid.payments.infrastructure.adapters.in.web.exception;

import com.solid.payments.domain.port.exception.PaymentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

// A anotação @ControllerAdvice permite que esta classe capture exceções de todos os controllers.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Este metodo será chamado sempre que uma PaymentNotFoundException for lançada.
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        // Retorna um HTTP 404 Not Found com uma mensagem clara no corpo da resposta.
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    // Poderíamos adicionar outros handlers aqui para diferentes exceções.
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Map<String, String>> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        // Retorna um HTTP 400 Bad Request
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, String>> handleIllegalStateException(IllegalStateException ex) {
        // Retorna um HTTP 422 Unprocessable Entity, ideal para regras de negócio violadas.
        return new ResponseEntity<>(
                Map.of("error", ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
