package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.domain.entity.Address;
import br.com.fooddelivery.tialudeliveryback.domain.repository.AddressRepository;
import br.com.fooddelivery.tialudeliveryback.dto.AddressListResponse;
import br.com.fooddelivery.tialudeliveryback.dto.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressListResponse listAddresses(String userId) {
        List<Address> addresses = addressRepository.findByUserIdOrderByPadraoEntregaDesc(userId);
        List<AddressResponse> addressResponses = addresses.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        
        return new AddressListResponse(addressResponses.size(), addressResponses);
    }

    private AddressResponse toResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getCep(),
                address.getTipoLogradouro(),
                address.getLogradouro(),
                address.getNumero(),
                address.getBairro(),
                address.getCidade(),
                address.getEstado(),
                address.getComplemento(),
                address.getTipo(),
                address.isPadraoEntrega()
        );
    }
}