package br.com.fooddelivery.tialudeliveryback.exception;

import br.com.fooddelivery.tialudeliveryback.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HorarioInvalidoException.class)
    public ResponseEntity<Object> handleHorarioInvalido(HorarioInvalidoException ex) {

        Map<String, Object> erro = new HashMap<>();
        erro.put("codigo", "ERRO_VALIDACAO_HORARIO");
        erro.put("detalhe", "Erro no cadastro de horários.");
        erro.put("campos_com_erro", ex.getErros());

        Map<String, Object> body = new HashMap<>();
        body.put("erro", erro);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<Object> handleTokenInvalido(TokenInvalidoException ex) {

        Map<String, Object> erro = new HashMap<>();
        erro.put("codigo", "NAO_AUTORIZADO");
        erro.put("detalhe", "Acesso negado. Token de estabelecimento inválido.");

        Map<String, Object> body = new HashMap<>();
        body.put("erro", erro);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorDTO error = new ErrorDTO();
        error.setCodigoErro("RESOURCE_NOT_FOUND");
        error.setMensagem(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}