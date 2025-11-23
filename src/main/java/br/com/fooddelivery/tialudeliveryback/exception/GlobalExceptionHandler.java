package br.com.fooddelivery.tialudeliveryback.exception;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentMethodNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePaymentMethodNotFound(PaymentMethodNotFoundException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.create(
                "MEIO_PAGAMENTO_NAO_ENCONTRADO",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({UnauthorizedException.class, MissingRequestHeaderException.class})
    public ResponseEntity<ErrorResponseDTO> handleUnauthorized(Exception ex) {
        ErrorResponseDTO error = ErrorResponseDTO.create(
                "NAO_AUTORIZADO",
                "Token de autenticação ausente ou inválido."
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
