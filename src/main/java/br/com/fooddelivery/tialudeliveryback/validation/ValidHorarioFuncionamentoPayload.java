package br.com.fooddelivery.tialudeliveryback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HorarioFuncionamentoPayloadValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHorarioFuncionamentoPayload {

    String message() default "O payload deve conter exatamente um registro para cada dia da semana.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
