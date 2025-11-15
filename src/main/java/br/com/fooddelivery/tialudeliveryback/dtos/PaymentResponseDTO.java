package br.com.fooddelivery.tialudeliveryback.dtos;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private String idMeioPagamento;
    private String mensagem;
    private DetalhesCartao detalhesCartao;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetalhesCartao {
        private String tipo;
        private String bandeira;
        private String ultimosDigitos;
        private String nomeExibicao;
    }
}