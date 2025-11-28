package br.com.fooddelivery.tialudeliveryback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/estabelecimentos/**")
public class OpeningHoursController {


    @PutMapping("/{idEstabelecimento}/opening-hours")
    @PreAuthorize("#idEstabelecimento == authentication.token.claims['estabelecimento_id']")
    public ResponseEntity<?> atualizarHorarios(
            @PathVariable String idEstabelecimento,
            @RequestBody List<Map<String, Object>> payload,
            @AuthenticationPrincipal Jwt jwt
    ) {

        return ResponseEntity.noContent().build();
    }
}