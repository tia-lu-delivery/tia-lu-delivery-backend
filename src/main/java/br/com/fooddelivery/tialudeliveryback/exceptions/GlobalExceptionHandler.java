package br.com.fooddelivery.tialudeliveryback.exceptions;

import br.com.fooddelivery.tialudeliveryback.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

        // Mapear métodos de validação para os nomes corretos
        private String mapFieldName(String field) {
                return switch (field) {
                        case "luhnValid" -> "numeroCartao";
                        case "expirationDateValid" -> "validadeMes";
                        default -> field;
                };
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {

                var errors = exception.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> new ErrorResponseDTO.FieldErrorDetail(
                                                mapFieldName(error.getField()),
                                                error.getDefaultMessage()))
                                .collect(Collectors.toList());

                var response = new ErrorResponseDTO(
                                "VALIDATION_ERROR",
                                "Um ou mais dados do cartão estão inválidos ou ausentes.",
                                errors,
                                null // acaoSugerida não aparece no JSON por causa do @JsonInclude
                );

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        @ExceptionHandler(DuplicateCardException.class)
        public ResponseEntity<ErrorResponseDTO> handleDuplicateCard(DuplicateCardException exception) {

                var response = new ErrorResponseDTO(
                                "DUPLICATE_CARD",
                                "Este cartão já está cadastrado em sua carteira.",
                                null,
                                exception.getAcaoSugerida() // aparece somente se não for null
                );

                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
}