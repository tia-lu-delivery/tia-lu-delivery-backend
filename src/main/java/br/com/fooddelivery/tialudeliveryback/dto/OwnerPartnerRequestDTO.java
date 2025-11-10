package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPartnerRequestDTO {

    @NotBlank(message = "CPF não pode ser nulo ou vazio.")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos.")
    private String cpf;

    @NotBlank(message = "RG não pode ser nulo ou vazio.")
    private String rg;

    @NotBlank(message = "Órgão emissor do RG não pode ser nulo ou vazio.")
    private String orgaoEmissorRg;

    @NotBlank(message = "Email não pode ser nulo ou vazio.")
    @Email(message = "O formato do email é inválido.")
    private String email;

    @NotBlank(message = "Telefone não pode ser nulo ou vazio.")
    private String telefone;

    @NotBlank(message = "Nome completo não pode ser nulo ou vazio.")
    private String nomeCompleto;
}
