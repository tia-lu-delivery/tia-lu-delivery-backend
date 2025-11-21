package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO auxiliar que representa o objeto de endereço dentro da requisição.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    // Validação: Campo obrigatório e deve conter exatamente 8 dígitos numéricos (sem traço)
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos")
    private String cep;

    // Validação: Campo de texto obrigatório
    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    // Validação: Campo de texto obrigatório (pode ser "S/N" ou número)
    @NotBlank(message = "O número é obrigatório")
    private String numero;

    // Campo opcional, não recebe anotação @NotBlank
    private String complemento;

    // Validação: Campo de texto obrigatório
    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    // Validação: Campo de texto obrigatório
    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    // Validação: Campo obrigatório e deve ter exatamente 2 letras maiúsculas (Sigla UF)
    @NotBlank(message = "O estado é obrigatório")
    @Pattern(regexp = "[A-Z]{2}", message = "O estado deve ser a sigla (ex: SP)")
    private String estado;
}