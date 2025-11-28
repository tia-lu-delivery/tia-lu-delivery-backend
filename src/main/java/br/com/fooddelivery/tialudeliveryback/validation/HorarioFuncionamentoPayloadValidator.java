package br.com.fooddelivery.tialudeliveryback.validation;

import br.com.fooddelivery.tialudeliveryback.dto.DiaSemana;
import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoRequestDTO;
import br.com.fooddelivery.tialudeliveryback.exception.HorarioInvalidoException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;
import java.util.stream.Collectors;

public class HorarioFuncionamentoPayloadValidator implements ConstraintValidator<ValidHorarioFuncionamentoPayload, List<HorarioFuncionamentoRequestDTO>> {

    @Override
    public boolean isValid(List<HorarioFuncionamentoRequestDTO> value, ConstraintValidatorContext context) {

        List<Map<String, String>> erros = new ArrayList<>();

        if (value == null || value.isEmpty()) {
            adicionarErro(erros, "geral", "O payload não pode ser vazio e deve conter todos os dias da semana.");
            throw new HorarioInvalidoException(erros);
        }

        if (value.size() != 7) {
            adicionarErro(erros, "geral", "O payload deve conter exatamente 7 dias (segunda a domingo).");
            throw new HorarioInvalidoException(erros);
        }

        Set<DiaSemana> diasRecebidos = value.stream()
                .map(HorarioFuncionamentoRequestDTO::getDia)
                .collect(Collectors.toSet());

        if (!diasRecebidos.equals(EnumSet.allOf(DiaSemana.class))) {
            adicionarErro(erros, "geral", "O payload deve conter todos os dias da semana, sem repetição.");
            throw new HorarioInvalidoException(erros);
        }

        for (HorarioFuncionamentoRequestDTO dto : value) {
            validarHorarios(dto, erros);
        }

        if (!erros.isEmpty()) {
            throw new HorarioInvalidoException(erros);
        }

        return true;
    }

    private void adicionarMensagem(ConstraintValidatorContext context, String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg)
                .addConstraintViolation();
    }


    // NOVO: método para adicionar erro ao modelo de retorno
    private void adicionarErro(List<Map<String, String>> erros, String dia, String mensagem) {
        Map<String, String> erro = new HashMap<>();
        erro.put("dia", dia);
        erro.put("mensagem", mensagem);
        erros.add(erro);
    }


    //erick-------------------------

    private void validarHorarios(HorarioFuncionamentoRequestDTO dto, List<Map<String, String>> erros) {

        if (Boolean.TRUE.equals(dto.getAberto())) {
            if (dto.getHoraAbertura() == null || dto.getHoraFechamento() == null) {
                adicionarErro(erros, dto.getDia().name().toLowerCase(),
                        "Para dias abertos, hora_abertura e hora_fechamento são obrigatórios.");
                return;
            }

            // Valida se hora_fechamento é posterior à hora_abertura
            if (dto.getHoraFechamento().compareTo(dto.getHoraAbertura()) <= 0) {
                adicionarErro(erros, dto.getDia().name().toLowerCase(),
                        String.format("Hora de fechamento (%s) deve ser posterior à hora de abertura (%s).",
                                dto.getHoraFechamento(), dto.getHoraAbertura()));
            }
        }
    }

}
//-----------------------------