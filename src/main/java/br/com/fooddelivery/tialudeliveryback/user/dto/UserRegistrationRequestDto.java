package br.com.fooddelivery.tialudeliveryback.user.dto;

import br.com.fooddelivery.tialudeliveryback.validation.Adult;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRegistrationRequestDto(
	@NotBlank(message = "O nome completo é obrigatório.")
	String nomeCompleto,

	@NotNull(message = "A data de nascimento é obrigatória.")
	@Adult(18)
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate dataNascimento,

	@NotBlank(message = "O e-mail é obrigatório.")
	@Email(message = "Formato de e-mail inválido.")
	String email,

	@NotBlank(message = "A senha é obrigatória.")
	@Pattern(
		regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
		message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma minúscula e um número."
	)
	String senha,

	@NotNull(message = "O endereço é obrigatório.")
	@Valid
	EnderecoRequestDto endereco
) {
}
