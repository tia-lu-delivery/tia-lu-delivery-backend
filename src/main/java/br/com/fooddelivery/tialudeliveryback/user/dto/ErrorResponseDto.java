package br.com.fooddelivery.tialudeliveryback.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private Erro erro;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Erro {
        private String codigo;
        private String detalhe;
        private List<CampoErro> campos_com_erro;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CampoErro {
        private String campo;
        private String mensagem;
    }
}
