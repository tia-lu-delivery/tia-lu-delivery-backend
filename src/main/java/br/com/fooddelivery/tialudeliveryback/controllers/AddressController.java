package br.com.fooddelivery.tialudeliveryback.controllers;

import br.com.fooddelivery.tialudeliveryback.dtos.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.dtos.AddressErrorResponseDTO;
import br.com.fooddelivery.tialudeliveryback.exceptions.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<?> listAddresses(
            @RequestHeader("UserId") Long userId) {
        List<AddressResponseDTO> lista = addressService.listAllAddresses(userId);
        return ResponseEntity.ok(lista);
    }

    @PatchMapping("/{id_endereco}/set-principal")
    public ResponseEntity<?> setPrincipalAddress(
            @PathVariable("id_endereco") Long idEndereco,
            @RequestHeader("UserId") Long userId) {
        try {
            return ResponseEntity.ok(addressService.setAddressAsPrincipal(idEndereco, userId));
        } catch (AddressNotFoundException e) {
            AddressErrorResponseDTO errorDTO = new AddressErrorResponseDTO(
                    "ENDERECO_NAO_ENCONTRADO",
                    "O endereço especificado não existe ou não pertence ao usuário.");
            Map<String, Object> erroResponse = Map.of("erro", errorDTO);
            return ResponseEntity.status(404).body(erroResponse);
        }
    }
}