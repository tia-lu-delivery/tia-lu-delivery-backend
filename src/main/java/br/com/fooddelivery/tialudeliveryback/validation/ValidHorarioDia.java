package br.com.fooddelivery.tialudeliveryback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HorarioDiaValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHorarioDia {

    String message() default "Horário inválido para o dia informado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
