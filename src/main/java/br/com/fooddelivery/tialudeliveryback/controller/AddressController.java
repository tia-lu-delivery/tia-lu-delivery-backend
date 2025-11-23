package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.AddressListResponse;
import br.com.fooddelivery.tialudeliveryback.exception.UnauthorizedException;
import br.com.fooddelivery.tialudeliveryback.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<AddressListResponse> listAddresses(@RequestHeader(value = "X-User-Id", required = false) String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new UnauthorizedException("Token de autenticação ausente ou inválido. Faça login novamente.");
        }
        AddressListResponse addresses = addressService.listAddresses(userId);
        return ResponseEntity.ok(addresses);
    }
}

