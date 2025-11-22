package com.seuprojeto.api.controller;

import com.seuprojeto.api.dto.request.AddressDeleteRequestDTO;
import com.seuprojeto.api.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @DeleteMapping("/{id_endereco}")
    public ResponseEntity<?> deletarEndereco(
            @PathVariable("id_endereco") Long idEndereco,
            @Valid @RequestBody AddressDeleteRequestDTO request) {

        addressService.deletarEndereco(idEndereco, request);

        return ResponseEntity.ok(
                String.format("Endereço ID %d deletado com sucesso.", idEndereco)
        );
    }
}
