package br.com.fooddelivery.tialudeliveryback.dto;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerPartnerRequestDTO {

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    @Size(max = 20, message = "RG deve ter no máximo 20 caracteres")
    private String rg;

    @NotBlank(message = "Órgão emissor do RG é obrigatório")
    @Size(max = 20, message = "Órgão emissor deve ter no máximo 20 caracteres")
    private String orgaoEmissorRg;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 200, message = "Nome completo deve ter entre 3 e 200 caracteres")
    private String nomeCompleto;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve seguir o formato nome@dominio.com.br")
    @Size(max = 100, message = "E-mail deve ter no máximo 100 caracteres")
    private String email;

    @NotBlank(message = "Telefone (WhatsApp) é obrigatório")
    @Pattern(regexp = "\\d{11,14}", message = "Telefone deve conter entre 11 e 14 dígitos (DDD + número ou formato internacional)")
    private String telefone;
}