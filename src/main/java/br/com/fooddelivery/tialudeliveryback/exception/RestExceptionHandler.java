package br.com.fooddelivery.tialudeliveryback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String,Object> body = new HashMap<>();
        Map<String,String> err = new HashMap<>();
        err.put("codigo", "MEIO_PAGAMENTO_NAO_ENCONTRADO");
        err.put("detalhe", ex.getMessage());
        body.put("erro", err);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> handleValidation(ValidationException ex) {
        Map<String,Object> body = new HashMap<>();
        Map<String,Object> err = new HashMap<>();
        err.put("codigo", "ERRO_VALIDACAO_CARTAO");
        err.put("detalhe", ex.getMessage());

        List<Map<String,String>> campos = ex.getCamposComErro().stream()
                .map(f -> {
                    Map<String,String> m = new HashMap<>();
                    m.put("campo", f.getCampo());
                    m.put("mensagem", f.getMensagem());
                    return m;
                })
                .collect(Collectors.toList());

        err.put("campos_com_erro", campos);
        body.put("erro", err);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
