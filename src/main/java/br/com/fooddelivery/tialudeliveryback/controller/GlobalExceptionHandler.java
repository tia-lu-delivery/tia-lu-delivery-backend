package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorEnvelope;
import br.com.fooddelivery.tialudeliveryback.exception.ProductNotFoundException;
import br.com.fooddelivery.tialudeliveryback.exception.UnauthorizedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorEnvelope handleProductNotFound(ProductNotFoundException ex) {
        return ErrorEnvelope.of("PRODUTO_NAO_ENCONTRADO", ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorEnvelope handleUnauthorized(UnauthorizedException ex) {
        String detalhe = ex.getMessage() != null ? ex.getMessage() : "Acesso não autorizado.";
        return ErrorEnvelope.of("NAO_AUTORIZADO", detalhe);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorEnvelope> handleResponseStatus(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        if (status == null) status = HttpStatus.INTERNAL_SERVER_ERROR;

        String codigo;
        switch (status) {
            case NOT_FOUND -> codigo = "RECURSO_NAO_ENCONTRADO";
            case UNAUTHORIZED -> codigo = "NAO_AUTORIZADO";
            default -> codigo = "ERRO";
        }
        return ResponseEntity.status(status)
                .body(ErrorEnvelope.of(codigo, ex.getReason() != null ? ex.getReason() : ""));
    }
}
