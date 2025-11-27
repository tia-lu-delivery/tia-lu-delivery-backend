package br.com.fooddelivery.tialudeliveryback.services;

import br.com.fooddelivery.tialudeliveryback.dtos.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.SetPrincipalAddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exceptions.AddressNotFoundException;
import java.util.List;

public interface AddressService {
    /**
     * Define um endereço como principal do usuário
     *
     * @param idEndereco ID do endereço
     * @param idUsuario ID do usuário
     * @return SetPrincipalAddressResponseDTO com confirmação da operação
     * @throws AddressNotFoundException se o endereço não existir ou não pertencer ao usuário
     */
    SetPrincipalAddressResponseDTO setAddressAsPrincipal(Long idEndereco, Long idUsuario)
            throws AddressNotFoundException;

    /**
     * Lista todos os endereços de um usuário
     *
     * @param idUsuario ID do usuário
     * @return Lista de endereços do usuário
     */
    List<AddressResponseDTO> listAllAddresses(Long idUsuario);
}