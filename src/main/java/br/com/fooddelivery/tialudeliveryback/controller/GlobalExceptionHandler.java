package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorEnvelope;
import br.com.fooddelivery.tialudeliveryback.exception.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.exception.ErrorCode;
import br.com.fooddelivery.tialudeliveryback.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorEnvelope> handleAddressNotFound(AddressNotFoundException ex) {
        ErrorEnvelope envelope = ErrorEnvelope.builder()
                .erro(ErrorEnvelope.Error.builder()
                        .codigo(ErrorCode.ENDERECO_NAO_ENCONTRADO.name())
                        .detalhe(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(envelope);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorEnvelope> handleUnauthorized(UnauthorizedException ex) {
        ErrorEnvelope envelope = ErrorEnvelope.builder()
                .erro(ErrorEnvelope.Error.builder()
                        .codigo(ErrorCode.NAO_AUTORIZADO.name())
                        .detalhe(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(envelope);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEnvelope> handleValidation(MethodArgumentNotValidException ex) {
        ErrorEnvelope envelope = ErrorEnvelope.builder()
                .erro(ErrorEnvelope.Error.builder()
                        .codigo(ErrorCode.ERRO_VALIDACAO.name())
                        .detalhe("Os dados fornecidos são inválidos.")
                        .campos_com_erro(ex.getBindingResult().getFieldErrors().stream()
                                .map(f -> ErrorEnvelope.FieldErrorDetail.builder()
                                        .campo(f.getField())
                                        .mensagem(f.getDefaultMessage())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .build();
        return ResponseEntity.badRequest().body(envelope);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorEnvelope> handleGeneric(Exception ex) {
        ErrorEnvelope envelope = ErrorEnvelope.builder()
                .erro(ErrorEnvelope.Error.builder()
                        .codigo(ErrorCode.ERRO.name())
                        .detalhe(ex.getMessage())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(envelope);
    }
}
