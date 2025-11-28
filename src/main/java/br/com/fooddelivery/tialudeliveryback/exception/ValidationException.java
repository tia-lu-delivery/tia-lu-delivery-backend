package br.com.fooddelivery.tialudeliveryback.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<FieldValidationError> camposComErro;

    public ValidationException(String message, List<FieldValidationError> camposComErro) {
        super(message);
        this.camposComErro = camposComErro;
    }

    public List<FieldValidationError> getCamposComErro() {
        return camposComErro;
    }
}
