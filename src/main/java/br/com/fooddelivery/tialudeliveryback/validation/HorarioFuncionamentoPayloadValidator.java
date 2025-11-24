package br.com.fooddelivery.tialudeliveryback.validation;

import br.com.fooddelivery.tialudeliveryback.dto.DiaSemana;
import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HorarioFuncionamentoPayloadValidator implements ConstraintValidator<ValidHorarioFuncionamentoPayload, List<HorarioFuncionamentoRequestDTO>> {

    @Override
    public boolean isValid(List<HorarioFuncionamentoRequestDTO> value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            adicionarMensagem(context, "O payload não pode ser vazio e deve conter todos os dias da semana.");
            return false;
        }

        if (value.size() != 7) {
            adicionarMensagem(context, "O payload deve conter exatamente 7 dias (segunda a domingo).");
            return false;
        }

        Set<DiaSemana> diasRecebidos = value.stream()
                .map(HorarioFuncionamentoRequestDTO::getDia)
                .collect(Collectors.toSet());

        if (!diasRecebidos.equals(EnumSet.allOf(DiaSemana.class))) {
            adicionarMensagem(context, "O payload deve conter todos os dias da semana, sem repetição.");
            return false;
        }

        return true;
    }

    private void adicionarMensagem(ConstraintValidatorContext context, String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg)
                .addConstraintViolation();
    }

    private boolean validarHorarios(HorarioFuncionamentoRequestDTO dto, ConstraintValidatorContext context) {
        if (Boolean.TRUE.equals(dto.getAberto())) {
            if (dto.getHoraAbertura() == null || dto.getHoraFechamento() == null) {
                adicionarMensagem(context, "Para dias abertos, hora_abertura e hora_fechamento são obrigatórios.");
                return false;
            }

            // Valida se hora_fechamento é posterior à hora_abertura
            if (dto.getHoraFechamento().compareTo(dto.getHoraAbertura()) <= 0) {
                adicionarMensagem(context,
                        String.format("Hora de fechamento (%s) deve ser posterior à hora de abertura (%s).",
                                dto.getHoraFechamento(), dto.getHoraAbertura()));
                return false;
            }
        }
        return true;
    }

}
