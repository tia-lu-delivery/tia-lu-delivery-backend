package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Data;

@JsonInclude(Include.NON_NULL) // Garante que campos nulos (como 'campo' ou 'detalhes') não apareçam no JSON
@Data
public class ErrorEnvelope {

    private String codigoErro;
    private String mensagem;
    private String acaoSugerida; 
    private String campo;        
    private List<ErrorDetail> detalhes;

    // Construtor para 404 Not Found (Cenário 2)
    public ErrorEnvelope(String codigoErro, String mensagem, String acaoSugerida) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
        this.acaoSugerida = acaoSugerida;
    }

    // Construtor para 409 Conflict (Cenário 3)
    public ErrorEnvelope(String codigoErro, String mensagem, String campo, boolean isConflict) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
        this.campo = campo;
    }
    
    // Construtor para 400 Bad Request (Cenário 4)
    public ErrorEnvelope(String codigoErro, String mensagem, List<ErrorDetail> detalhes) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }
    

    // Classe interna para os detalhes de validação (Cenário 4)
    @Data
    public static class ErrorDetail {
        private String campo;
        private String erro;

        public ErrorDetail(String campo, String erro) {
            this.campo = campo;
            this.erro = erro;
        }
        // Se você não usar Lombok, adicione aqui os Getters/Setters
    }
}