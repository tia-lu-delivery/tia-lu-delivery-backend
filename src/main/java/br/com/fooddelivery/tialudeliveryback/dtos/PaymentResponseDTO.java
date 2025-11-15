package br.com.fooddelivery.tialudeliveryback.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    // Classe de teste

    private String idMeioPagamento;
    private String mensagem;
    private DetalhesCartao detalhesCartao;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetalhesCartao {
        private String tipo;
        private String bandeira;
        private String ultimosDigitos;
        private String nomeExibicao;
    }
}
