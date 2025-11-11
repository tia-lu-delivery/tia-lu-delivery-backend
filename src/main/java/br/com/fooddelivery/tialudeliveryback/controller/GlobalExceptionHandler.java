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
import br.com.fooddelivery.tialudeliveryback.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorEnvelope handleProductNotFound(ProductNotFoundException ex) {
        log.info("Produto não encontrado: {}", ex.getMessage());
        return ErrorEnvelope.of(ErrorCode.PRODUTO_NAO_ENCONTRADO.toString(), ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorEnvelope handleUnauthorized(UnauthorizedException ex) {
        String detalhe = ex.getMessage() != null ? ex.getMessage() : "Acesso não autorizado.";
        log.info("Acesso não autorizado: {}", detalhe);
        return ErrorEnvelope.of(ErrorCode.NAO_AUTORIZADO.toString(), detalhe);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorEnvelope> handleResponseStatus(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        if (status == null) status = HttpStatus.INTERNAL_SERVER_ERROR;

        String codigo;
        switch (status) {
            case NOT_FOUND -> codigo = ErrorCode.RECURSO_NAO_ENCONTRADO.toString();
            case UNAUTHORIZED -> codigo = ErrorCode.NAO_AUTORIZADO.toString();
            default -> codigo = ErrorCode.ERRO.toString();
        }
        String detalhe = ex.getReason() != null ? ex.getReason() : "";
        log.warn("ResponseStatusException: status={} reason={}", status, detalhe);
        return ResponseEntity.status(status)
                .body(ErrorEnvelope.of(codigo, detalhe));
    }
}
