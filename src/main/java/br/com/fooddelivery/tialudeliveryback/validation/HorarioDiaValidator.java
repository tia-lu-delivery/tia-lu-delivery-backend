package br.com.fooddelivery.tialudeliveryback.validation;

import br.com.fooddelivery.tialudeliveryback.dto.HorarioFuncionamentoRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class HorarioDiaValidator implements ConstraintValidator<ValidHorarioDia, HorarioFuncionamentoRequestDTO> {

    @Override
    public boolean isValid(HorarioFuncionamentoRequestDTO dto, ConstraintValidatorContext context) {

        if (dto == null) return true;

        if (dto.getAberto() == null) {
            return erro(context, "O campo 'aberto' é obrigatório para o dia " + dto.getDia());
        }

        if (!dto.getAberto()) {
            if (dto.getHoraAbertura() != null || dto.getHoraFechamento() != null) {
                return erro(context,
                        "Dia " + dto.getDia() + ": se 'aberto' = false, horas devem ser nulas.");
            }
            return true;
        }

        if (dto.getHoraAbertura() == null || dto.getHoraFechamento() == null) {
            return erro(context,
                    "Dia " + dto.getDia() + ": horários são obrigatórios quando 'aberto' = true.");
        }

        LocalTime abertura;
        LocalTime fechamento;

        try {
            abertura = LocalTime.parse(dto.getHoraAbertura());
            fechamento = LocalTime.parse(dto.getHoraFechamento());
        } catch (DateTimeParseException e) {
            return erro(context,
                    "Dia " + dto.getDia() + ": horários devem estar no formato HH:MM.");
        }

        if (!fechamento.isAfter(abertura)) {
            return erro(context,
                    "Dia " + dto.getDia() +
                            ": hora_fechamento deve ser posterior à hora_abertura.");
        }

        return true;
    }

    private boolean erro(ConstraintValidatorContext context, String msg) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        return false;
    }
}
