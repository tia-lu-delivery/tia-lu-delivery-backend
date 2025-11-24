package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoResponseDTO;
import br.com.fooddelivery.tialudeliveryback.validation.ValidHorarioFuncionamentoPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/merchant")
public class OpeningHoursController {

    @PutMapping("/{idEstabelecimento}/opening-hours")
    @PreAuthorize("#idEstabelecimento == authentication.token.claims['merchant_id']")
    public ResponseEntity<HorarioFuncionamentoResponseDTO> atualizarHorarios(
            @PathVariable String idEstabelecimento,
            @RequestBody @Valid @ValidHorarioFuncionamentoPayload List<HorarioFuncionamentoRequestDTO> payload,
            @AuthenticationPrincipal Jwt jwt
    ) {

        HorarioFuncionamentoResponseDTO response = new HorarioFuncionamentoResponseDTO(
                idEstabelecimento,
                "atualizado",
                "Horários de funcionamento semanais atualizados com sucesso.",
                getDiasAbertos(payload)
        );

        return ResponseEntity.ok(response);
    }

    private List<String> getDiasAbertos(List<HorarioFuncionamentoRequestDTO> payload) {
        return payload.stream()
                .filter(HorarioFuncionamentoRequestDTO::getAberto)
                .map(dto -> dto.getDia().getValue())
                .collect(Collectors.toList());
    }
}