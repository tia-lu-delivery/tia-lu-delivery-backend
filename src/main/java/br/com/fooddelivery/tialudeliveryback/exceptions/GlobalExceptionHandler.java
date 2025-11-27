package br.com.foodelivery.tialudeliveryback.exceptions;

import br.com.foodelivery.tialudeliveryback.domain.exceptions.InactivePaymentMethodException;
import br.com.foodelivery.tialudeliveryback.domain.exceptions.PaymentMethodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentMethodNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(PaymentMethodNotFoundException ex) {
        Map<String, Object> erro = new HashMap<>();
        Map<String, String> inner = new HashMap<>();
        inner.put("codigo", "MEIO_PAGAMENTO_NAO_ENCONTRADO");
        inner.put("detalhe", "O meio de pagamento com ID '" + ex.getPaymentMethodId() + "' não foi encontrado ou não pertence a este usuário.");
        erro.put("erro", inner);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(InactivePaymentMethodException.class)
    public ResponseEntity<Map<String, Object>> handleInactive(InactivePaymentMethodException ex) {
        Map<String, Object> erro = new HashMap<>();
        Map<String, String> inner = new HashMap<>();
        inner.put("codigo", "MEIO_PAGAMENTO_INATIVO");
        inner.put("detalhe", "Não é possível definir um meio de pagamento inativo como principal.");
        erro.put("erro", inner);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}