package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.entity.HorarioFuncionamentoEntity;
import br.com.fooddelivery.tialudeliveryback.repository.EstabelecimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public void atualizarHorarios(String idEstabelecimento,
                                  List<HorarioFuncionamentoRequestDTO> payload) {

        var est = estabelecimentoRepository.findById(idEstabelecimento)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        // converter DTOs em entidades caso seja necessario
        est.setHorarios(converterParaEntidade(payload));

        estabelecimentoRepository.save(est);
    }

    private List<HorarioFuncionamentoEntity> converterParaEntidade(
            List<HorarioFuncionamentoRequestDTO> lista
    ) {
        return lista.stream()
                .map(dto -> HorarioFuncionamentoEntity.builder()
                        .dia(dto.getDia())
                        .aberto(dto.getAberto())
                        .horaAbertura(dto.getHoraAbertura())
                        .horaFechamento(dto.getHoraFechamento())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
