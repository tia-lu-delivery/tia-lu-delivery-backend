package br.com.fooddelivery.tialudeliveryback.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDto(
	@NotBlank(message = "O CEP é obrigatório.")
	@Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos.")
	String cep,

	@NotBlank(message = "O logradouro é obrigatório.")
	String logradouro,

	@NotBlank(message = "O número é obrigatório.")
	String numero,

	@Size(max = 255, message = "O complemento deve ter no máximo 255 caracteres.")
	String complemento,

	@NotBlank(message = "O bairro é obrigatório.")
	String bairro,

	@NotBlank(message = "A cidade é obrigatória.")
	String cidade,

	@NotBlank(message = "O estado é obrigatório.")
	@Size(min = 2, max = 2, message = "O estado deve ser informado com a sigla de 2 caracteres.")
	String estado
) {
}
