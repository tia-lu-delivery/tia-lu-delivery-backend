package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.OwnerPartnerRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.OwnerPartnerResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.OwnerPartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant/{idEstabelecimento}/owner-partner")
public class OwnerPartnerController {
    private final OwnerPartnerService service;

    @PostMapping
    public ResponseEntity<OwnerPartnerResponseDTO> create(@PathVariable String idEstabelecimento, @Valid @RequestBody OwnerPartnerRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.service.createOwnerPartner(idEstabelecimento, request));
    }

}