package br.com.fooddelivery.tialudeliveryback.mapper;

import br.com.fooddelivery.tialudeliveryback.domain.Address;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateResponse;

public class AddressMapper {

    public static AddressUpdateResponse toResponse(Address address) {
        return AddressUpdateResponse.builder()
                .id_endereco(address.getId())
                .status("atualizado")
                .endereco_atualizado(AddressUpdateResponse.AddressPayload.builder()
                        .cep(address.getCep())
                        .logradouro(address.getLogradouro())
                        .numero(address.getNumero())
                        .bairro(address.getBairro())
                        .cidade(address.getCidade())
                        .estado(address.getEstado())
                        .complemento(address.getComplemento())
                        .tipo(address.getTipo())
                        .padrao_entrega(address.isPadraoEntrega())
                        .tipo_logradouro(address.getTipoLogradouro())
                        .build())
                .build();
    }
}
