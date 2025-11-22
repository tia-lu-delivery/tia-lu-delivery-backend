package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada (Request) para o cadastro de novos estabelecimentos.
 * Responsável por receber e validar os dados antes de chegar no Controller.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantRequestDTO {

    // Validação: Obrigatório e restrito a 14 dígitos numéricos (formato padrão de CNPJ sem máscara)
    @NotBlank(message = "O CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos apenas números")
    private String cnpj;

    // Validação: Obrigatório e limitado a 100 caracteres para compatibilidade com o banco
    @NotBlank(message = "A Razão Social é obrigatória")
    @Size(max = 100, message = "A Razão Social deve ter no máximo 100 caracteres")
    private String razaoSocial;

    // Validação: Obrigatório e limitado a 100 caracteres
    @NotBlank(message = "O Nome Fantasia é obrigatório")
    @Size(max = 100, message = "O Nome Fantasia deve ter no máximo 100 caracteres")
    private String nomeFantasia;

    // Campo opcional (Inscrição Estadual), pode ser nulo ou vazio
    private String ie;

    // Validação: Objeto obrigatório.
    // A anotação @Valid instrui o Spring a entrar na classe EnderecoDTO e validar os campos dela também (validação em cascata).
    @NotNull(message = "O endereço é obrigatório")
    @Valid
    private EnderecoDTO endereco;
}