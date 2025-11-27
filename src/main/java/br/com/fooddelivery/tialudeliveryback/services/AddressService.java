package br.com.fooddelivery.tialudeliveryback.services;

import br.com.fooddelivery.tialudeliveryback.dtos.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.SetPrincipalAddressResponseDTO;
import java.util.List;

public interface AddressService {
    SetPrincipalAddressResponseDTO setAddressAsPrincipal(Long idEndereco, Long idUsuario)
            throws RuntimeException;

    List<AddressResponseDTO> listAllAddresses(Long idUsuario);
}