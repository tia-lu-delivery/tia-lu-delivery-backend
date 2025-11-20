package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.MerchantRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.MerchantResponseDTO;
import br.com.fooddelivery.tialudeliveryback.service.MerchantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    // Endpoint de criação (Task #153)
    @PostMapping
    public ResponseEntity<MerchantResponseDTO> create(@RequestBody MerchantRequestDTO request) {
        MerchantResponseDTO response = merchantService.createMerchant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MerchantResponseDTO>> getAll() {
        return ResponseEntity.ok(merchantService.findAll());
    }
}