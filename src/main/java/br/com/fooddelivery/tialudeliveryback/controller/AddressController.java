package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.AddressResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/address/{id_endereco}")
public class AddressController {
    final private AddressService service;

    @DeleteMapping
    public ResponseEntity<AddressResponseDTO> excluirEndereco(
            @PathVariable("id_endereco") Long idEndereco,
            Authentication authentication
    ) {
        Long idUsuario = extrairIdUsuario(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.service.excluirEndereco(idEndereco, idUsuario));
    }

    private Long extrairIdUsuario(Authentication authentication) {

        // Aqui ocorre a chamada do método authentication.getPrincipal().
        // Como o recurso de autenticação depende da implementação de um Usuário,
        // Apenas simularemos a extração do ID.

        return 1L;

    }

}