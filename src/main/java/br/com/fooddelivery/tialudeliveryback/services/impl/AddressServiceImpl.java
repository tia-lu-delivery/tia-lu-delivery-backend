package br.com.fooddelivery.tialudeliveryback.services.impl;

import br.com.fooddelivery.tialudeliveryback.dtos.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.SetPrincipalAddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exceptions.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.models.Address;
import br.com.fooddelivery.tialudeliveryback.repositories.AddressRepository;
import br.com.fooddelivery.tialudeliveryback.services.AddressService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação real da AddressService
 *
 * Esta classe é a implementação principal e deve ser usada quando a feature
 * DWOO-012 (Cadastro de Endereço) estiver completa e o banco de dados estiver
 * disponível.
 *
 * Esta implementação só será instanciada se AddressRepository estiver disponível
 * no contexto Spring (i.e., quando o JPA estiver configurado).
 *
 * Para desenvolvimento sem banco de dados, ative AddressServiceMock adicionando
 * a anotação @Service nela.
 */
@Service
@ConditionalOnBean(AddressRepository.class)
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public SetPrincipalAddressResponseDTO setAddressAsPrincipal(Long idEndereco, Long idUsuario)
            throws AddressNotFoundException {

        // Valida se o endereço existe e pertence ao usuário
        Address address = addressRepository.findByIdAndUserId(idEndereco, idUsuario)
                .orElseThrow(() -> new AddressNotFoundException(
                        "O endereço especificado não existe ou não pertence ao usuário."));

        // Desabilita todos os endereços principais do usuário
        addressRepository.unsetAllPrincipalAddressesByUserId(idUsuario);

        // Define o endereço selecionado como principal
        addressRepository.setPrincipalAddress(idEndereco);

        return new SetPrincipalAddressResponseDTO(
                "sucesso",
                "O endereço " + idEndereco + " foi definido como principal.",
                idEndereco
        );
    }

    @Override
    public List<AddressResponseDTO> listAllAddresses(Long idUsuario) {
        List<Address> addresses = addressRepository.findByUserId(idUsuario);

        return addresses.stream()
                .map(address -> new AddressResponseDTO(
                        address.getId(),
                        address.getLogradouro() + ", " + address.getNumero(),
                        address.isPadraoEntrega()
                ))
                .collect(Collectors.toList());
    }
}
