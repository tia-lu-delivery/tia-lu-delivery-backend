package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorEnvelope;
import br.com.fooddelivery.tialudeliveryback.dto.ErrorEnvelope.ErrorDetail;
import br.com.fooddelivery.tialudeliveryback.exception.CardapioNotFoundException;
import br.com.fooddelivery.tialudeliveryback.exception.CategoriaConflictException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ... (Outros Handlers de exceções existentes) ...

    // NOVO HANDLER (CA 1.3 - 404 Not Found)
    // Resposta de Erro 2
    @ExceptionHandler(CardapioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorEnvelope handleCardapioNotFound(CardapioNotFoundException ex) {
        return new ErrorEnvelope(
            "RESOURCE_NOT_FOUND", 
            ex.getMessage(), 
            "Verifique o identificador do cardápio e tente novamente.");
    }

    // NOVO HANDLER (CA 1.4 - 409 Conflict)
    // Resposta de Erro 3
    @ExceptionHandler(CategoriaConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorEnvelope handleCategoriaConflict(CategoriaConflictException ex) {
        return new ErrorEnvelope(
            "DUPLICATE_NAME", 
            ex.getMessage(), 
            ex.getCampo(), 
            true); // true para usar o construtor 409
    }

    // NOVO HANDLER (CA 1.2 - 400 Bad Request - Falha de Validação)
    // Resposta de Erro 4
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEnvelope handleValidationErrors(MethodArgumentNotValidException ex) {
        
        // Mapeia todos os erros de campo para a lista de 'detalhes'
        List<ErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorDetail(
                        fieldError.getField(), 
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        
        return new ErrorEnvelope(
            "VALIDATION_ERROR", 
            "Um ou mais campos de validação falharam. Consulte 'detalhes' para mais informações.", 
            details);
    }
}