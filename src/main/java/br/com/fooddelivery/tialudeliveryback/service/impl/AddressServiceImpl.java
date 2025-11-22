package br.com.fooddelivery.tialudeliveryback.service.impl;

import br.com.fooddelivery.tialudeliveryback.domain.Address;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateRequest;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateResponse;
import br.com.fooddelivery.tialudeliveryback.exception.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.mapper.AddressMapper;
import br.com.fooddelivery.tialudeliveryback.repository.AddressRepository;
import br.com.fooddelivery.tialudeliveryback.service.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public AddressUpdateResponse updateAddress(Long idEndereco, AddressUpdateRequest request, String userId) {
        Address address = addressRepository.findByUserIdAndId(userId, idEndereco)
                .orElseThrow(() -> new AddressNotFoundException(idEndereco));

        // Atualizar campos (PUT completo, substitui estado final)
        address.setCep(request.getCep());
        address.setTipoLogradouro(request.getTipo_logradouro());
        address.setLogradouro(request.getLogradouro());
        address.setNumero(request.getNumero());
        address.setBairro(request.getBairro());
        address.setCidade(request.getCidade());
        address.setEstado(request.getEstado());
        address.setComplemento(request.getComplemento());
        address.setTipo(request.getTipo());

        if (Boolean.TRUE.equals(request.getPadrao_entrega())) {
            addressRepository.clearDefaultForUser(userId);
            address.setPadraoEntrega(true);
        } else if (Boolean.FALSE.equals(request.getPadrao_entrega())) {
            // Se explicitamente false, apenas desmarca
            address.setPadraoEntrega(false);
        }

        // Persistir alterações
        Address saved = addressRepository.save(address);
        return AddressMapper.toResponse(saved);
    }
}
