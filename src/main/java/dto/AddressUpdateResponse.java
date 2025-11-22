package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressUpdateResponse {
    private Long id_endereco;
    private String status;
    private AddressPayload endereco_atualizado;

    @Getter
    @Builder
    public static class AddressPayload {
        private String cep;
        private String logradouro;
        private String numero;
        private String bairro;
        private String cidade;
        private String estado;
        private String complemento;
        private String tipo;
        private boolean padrao_entrega;
        private String tipo_logradouro;
    }
}
