package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DiaSemana {

    SEGUNDA("segunda"),
    TERCA("terca"),
    QUARTA("quarta"),
    QUINTA("quinta"),
    SEXTA("sexta"),
    SABADO("sabado"),
    DOMINGO("domingo");

    private final String value;

    DiaSemana(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static DiaSemana fromValue(String value) {
        if (value == null) {
            return null;
        }

        for (DiaSemana dia : values()) {
            if (dia.value.equalsIgnoreCase(value)) {
                return dia;
            }
        }

        throw new IllegalArgumentException(
                "Dia inválido: " + value + ". Use: segunda, terca, quarta, quinta, sexta, sabado, domingo."
        );
    }
}
