package br.com.fooddelivery.tialudeliveryback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.RECORD_COMPONENT;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD, PARAMETER, METHOD, CONSTRUCTOR, ANNOTATION_TYPE, RECORD_COMPONENT, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {

	int value() default 18;

	String message() default "A idade mínima para registro é de {value} anos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
