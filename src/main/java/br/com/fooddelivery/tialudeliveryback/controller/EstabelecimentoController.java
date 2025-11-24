package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.validation.ValidHorarioFuncionamentoPayload;
import br.com.fooddelivery.tialudeliveryback.service.EstabelecimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @PutMapping("/{idEstabelecimento}/horarios")
    public ResponseEntity<?> atualizarHorarios(
            @PathVariable String idEstabelecimento,
            @Valid @ValidHorarioFuncionamentoPayload
            @RequestBody List<@Valid HorarioFuncionamentoRequestDTO> payload
    ) {

        estabelecimentoService.atualizarHorarios(idEstabelecimento, payload);

        return ResponseEntity.ok(
                Map.of(
                        "id_estabelecimento", idEstabelecimento,
                        "status", "atualizado",
                        "detalhe", "Horários de funcionamento semanais atualizados com sucesso.",
                        "dias_abertos", payload.stream()
                                .filter(HorarioFuncionamentoRequestDTO::getAberto)
                                .map(h -> h.getDia().toString().toLowerCase())
                                .toList()
                )
        );
    }
}
