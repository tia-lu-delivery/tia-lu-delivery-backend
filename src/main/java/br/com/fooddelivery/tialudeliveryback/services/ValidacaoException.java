package br.com.fooddelivery.tialudeliveryback.services;


import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Exceção para erros de validação (resultando em HTTP 400).
 */
public class ValidacaoException extends RuntimeException {

    private final Map<String, Object> errorResponse;

    public ValidacaoException(String campo, String mensagem) {
        super(mensagem); // A mensagem da exceção principal

        Map<String, Object> campoErro = new HashMap<>();
        campoErro.put("campo", campo);
        campoErro.put("mensagem", mensagem);

        Map<String, Object> erro = new HashMap<>();
        erro.put("codigo", "ERRO_VALIDACAO");
        erro.put("detalhe", "Os dados fornecidos são inválidos.");
        erro.put("campos_com_erro", List.of(campoErro)); // Lista com o erro

        this.errorResponse = Map.of("erro", erro);
    }

    public Map<String, Object> getErrorResponse() {
        return errorResponse;
    }
}