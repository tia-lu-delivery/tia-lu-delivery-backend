package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateRequest;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateResponse;
import br.com.fooddelivery.tialudeliveryback.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PutMapping("/{id_endereco}")
    public ResponseEntity<AddressUpdateResponse> updateAddress(@PathVariable("id_endereco") Long idEndereco,
                                                                @Valid @RequestBody AddressUpdateRequest request,
                                                                @RequestHeader(name = "X-User-Id", required = false) String userIdHeader) {
        // Autenticação provisória similar ao X-Merchant-Id
        if (userIdHeader == null || userIdHeader.isBlank()) {
            return ResponseEntity.status(401).build();
        }
        AddressUpdateResponse response = addressService.updateAddress(idEndereco, request, userIdHeader);
        return ResponseEntity.ok(response);
    }
}
