package br.com.fooddelivery.tialudeliveryback.exceptions;

import br.com.fooddelivery.tialudeliveryback.dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

        private String mapFieldName(String field) {
                return switch (field) {
                        case "luhnValid" -> "numeroCartao";
                        case "expirationDateValid" -> "validadeMes";
                        case "cardNumberLengthValid" -> "numeroCartao";
                        default -> field;
                };
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {

                var fieldErrors = exception.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(e -> new ErrorResponseDTO.FieldErrorDetail(
                                                mapFieldName(e.getField()),
                                                e.getDefaultMessage()))
                                .collect(Collectors.toList());

                var globalErrors = exception.getBindingResult()
                                .getGlobalErrors()
                                .stream()
                                .filter(e -> !(e instanceof FieldError))
                                .map(this::toFieldErrorDetailFromObjectError)
                                .collect(Collectors.toList());

                var all = new ArrayList<ErrorResponseDTO.FieldErrorDetail>();
                all.addAll(fieldErrors);
                all.addAll(globalErrors);

                var response = new ErrorResponseDTO(
                                "VALIDATION_ERROR",
                                "Um ou mais dados do cartão estão inválidos ou ausentes.",
                                all.isEmpty() ? null : all,
                                null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        private ErrorResponseDTO.FieldErrorDetail toFieldErrorDetailFromObjectError(ObjectError error) {
                String campo = extractPropertyName(error);
                campo = mapFieldName(campo);
                return new ErrorResponseDTO.FieldErrorDetail(campo, error.getDefaultMessage());
        }

        // Tenta extrair o nome da propriedade a partir dos codes do ObjectError
        private String extractPropertyName(ObjectError error) {
                if (error.getCodes() == null)
                        return error.getObjectName();

                return Arrays.stream(error.getCodes())
                                .map(code -> {
                                        // Ex: AssertTrue.paymentRequestDTO.luhnValid
                                        String[] parts = code.split("\\.");
                                        if (parts.length >= 3)
                                                return parts[2]; // normalmente a propriedade
                                        if (parts.length == 2)
                                                return parts[1];
                                        return null;
                                })
                                .filter(Objects::nonNull)
                                .filter(p -> !p.equalsIgnoreCase(error.getObjectName()))
                                .findFirst()
                                .orElse(error.getObjectName());
        }

        @ExceptionHandler(DuplicateCardException.class)
        public ResponseEntity<ErrorResponseDTO> handleDuplicateCard(DuplicateCardException exception) {
                var response = new ErrorResponseDTO(
                                "DUPLICATE_CARD",
                                "Este cartão já está cadastrado em sua carteira.",
                                null,
                                exception.getAcaoSugerida());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
}