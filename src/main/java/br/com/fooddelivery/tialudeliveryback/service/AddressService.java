package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateRequest;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateResponse;

public interface AddressService {
    AddressUpdateResponse updateAddress(Long idEndereco, AddressUpdateRequest request, String userId);
}
