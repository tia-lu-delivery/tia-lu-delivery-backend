package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorEnvelope {
    private Error erro;

    @Getter
    @Builder
    public static class Error {
        private String codigo;
        private String detalhe;
        @Builder.Default
        private List<FieldErrorDetail> campos_com_erro = null; // Somente para validação
    }

    @Getter
    @Builder
    public static class FieldErrorDetail {
        private String campo;
        private String mensagem;
    }
}
