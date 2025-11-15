package br.com.fooddelivery.tialudeliveryback.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // exclui campos null no JSON
public class ErrorResponseDTO {

    private String codigoErro;
    private String mensagem;
    private List<FieldErrorDetail> detalhes;
    private String acaoSugerida; // aparece apenas no erro 409

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldErrorDetail {
        private String campo;
        private String erro;
    }
}