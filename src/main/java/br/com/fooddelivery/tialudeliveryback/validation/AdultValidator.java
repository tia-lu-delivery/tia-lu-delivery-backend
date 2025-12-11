package br.com.fooddelivery.tialudeliveryback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

	private int minimumAge;

	@Override
	public void initialize(Adult constraintAnnotation) {
		this.minimumAge = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		LocalDate today = LocalDate.now();
		return !value.isAfter(today.minusYears(minimumAge));
	}
}
