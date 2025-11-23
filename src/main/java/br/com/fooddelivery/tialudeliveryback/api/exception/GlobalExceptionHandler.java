package br.com.fooddelivery.tialudeliveryback.api.exception;

import br.com.fooddelivery.tialudeliveryback.api.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata o caso em que o @RequestParam obrigatório 'plate' está faltando (HTTP 400)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingParam(MissingServletRequestParameterException ex) {

        String detalhe = "O parâmetro '" + ex.getParameterName() + "' (termo de pesquisa) é obrigatório.";
        
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            "PARAMETRO_OBRIGATORIO_FALTANDO",
            detalhe
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handler para UnauthorizedException (HTTP 401)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorized(UnauthorizedException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("codigo", "NAO_AUTORIZADO");
        erro.put("detalhe", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("erro", erro);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    // Aqui você pode adicionar outros handlers de exceção, se necessário
}
