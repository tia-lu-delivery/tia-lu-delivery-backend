package br.com.fooddelivery.tialudeliveryback.exception;

import java.util.List;
import java.util.Map;

public class HorarioInvalidoException extends RuntimeException {

    private final List<Map<String, String>> erros;

    public HorarioInvalidoException(List<Map<String, String>> erros) {
        this.erros = erros;
    }

    public List<Map<String, String>> getErros() {
        return erros;
    }
}