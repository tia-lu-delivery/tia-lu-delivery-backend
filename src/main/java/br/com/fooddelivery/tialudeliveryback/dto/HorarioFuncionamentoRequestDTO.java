package br.com.fooddelivery.tialudeliveryback.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class HorarioFuncionamentoRequestDTO {

    @NotNull(message = "O campo 'dia' é obrigatório.")
    private DiaSemana dia;

    @NotNull(message = "O campo 'aberto' é obrigatório.")
    private Boolean aberto;

    @JsonProperty("hora_abertura")
    @Pattern(
            regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$",
            message = "Formato inválido para 'hora_abertura'. Use HH:MM."
    )
    private String horaAbertura;

    @JsonProperty("hora_fechamento")
    @Pattern(
            regexp = "^([01][0-9]|2[0-3]):[0-5][0-9]$",
            message = "Formato inválido para 'hora_fechamento'. Use HH:MM."
    )
    private String horaFechamento;

    public DiaSemana getDia() {
        return dia;
    }

    public void setDia(DiaSemana dia) {
        this.dia = dia;
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(String horaFechamento) {
        this.horaFechamento = horaFechamento;
    }
}
