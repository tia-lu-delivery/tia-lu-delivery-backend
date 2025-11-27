package br.com.fooddelivery.tialudeliveryback.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fooddelivery.tialudeliveryback.dto.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exception.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.models.Address;
import br.com.fooddelivery.tialudeliveryback.repository.AddressRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;
    @Transactional
    public AddressResponseDTO excluirEndereco(Long idEndereco, Long idUsuario) {
        log.info("Iniciando exclusão do endereço {} para o usuário {}", idEndereco, idUsuario);

        Address endereco = addressRepository.findByIdEnderecoAndIdUsuario(idEndereco, idUsuario)
                .orElseThrow(() -> {
                    log.warn("Endereço {} não encontrado para o usuário {}", idEndereco, idUsuario);
                    return new AddressNotFoundException(idEndereco);
                });

        if (Boolean.TRUE.equals(endereco.getIsEnderecoPadrao())) {
            log.info("Removendo status de endereço padrão antes da exclusão");
            addressRepository.removerEnderecoPadraoDoUsuario(idUsuario);
        }

        addressRepository.delete(endereco);
        log.info("Endereço {} excluído com sucesso", idEndereco);

        return AddressResponseDTO.sucesso(idEndereco);
    }
}
