package br.com.fooddelivery.tialudeliveryback.exception;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorDetailDTO;
import br.com.fooddelivery.tialudeliveryback.dto.ErrorResponseDTO;
import org.springframework.http.converter.HttpMessageNotReadableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("Erro de validação: {}", ex.getMessage());

        List<ErrorDetailDTO> detalhes = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            detalhes.add(ErrorDetailDTO.builder()
                    .campo(error.getField())
                    .erro(error.getDefaultMessage())
                    .build());
        }

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .codigoErro("VALIDATION_ERROR")
                .mensagem("Um ou mais campos do sócio proprietário contêm erros de validação.")
                .detalhes(detalhes)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Recurso não encontrado: {}", ex.getMessage());

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .codigoErro("RESOURCE_NOT_FOUND")
                .mensagem(ex.getMessage())
                .detalhes(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException ex) {
        log.error("Erro de negócio: {}", ex.getMessage());

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .codigoErro("BUSINESS_ERROR")
                .mensagem(ex.getMessage())
                .detalhes(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleJsonParseException(HttpMessageNotReadableException ex) {
        log.error("Erro na requisição: {}", ex.getMessage());

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .codigoErro("JSON_FORMAT_ERROR")
                .mensagem(ex.getMessage())
                .detalhes(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        log.error("Erro interno do servidor: ", ex);

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .codigoErro("INTERNAL_SERVER_ERROR")
                .mensagem("Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.")
                .detalhes(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}