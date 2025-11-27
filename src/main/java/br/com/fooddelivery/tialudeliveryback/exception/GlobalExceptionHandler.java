package br.com.fooddelivery.tialudeliveryback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEnderecoNaoEncontrado(AddressNotFoundException ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.of(
                "ENDERECO_NAO_ENCONTRADO",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.of(
                "ERRO_INTERNO",
                "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
