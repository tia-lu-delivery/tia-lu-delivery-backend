package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressUpdateRequest {
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Formato de CEP inválido. O CEP deve seguir o padrão 00000-000.")
    @NotBlank(message = "CEP é obrigatório.")
    private String cep;

    @NotBlank(message = "Tipo de logradouro é obrigatório.")
    private String tipo_logradouro;

    @NotBlank(message = "Logradouro é obrigatório.")
    private String logradouro;

    @NotBlank(message = "Número do logradouro é obrigatório.")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório.")
    private String estado;

    private String complemento;

    @NotBlank(message = "Tipo é obrigatório.")
    private String tipo;

    private Boolean padrao_entrega; // Pode ser null (não informado)
}
