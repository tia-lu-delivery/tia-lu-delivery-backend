package br.com.fooddelivery.tialudeliveryback.dtos;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    @NotBlank(message = "O número do cartão não pode estar vazio.")
    @Pattern(regexp = "\\d{13,16}", message = "O número do cartão deve conter entre 13 e 16 dígitos.")
    private String numeroCartao;

    @NotNull(message = "O mês de validade não pode ser nulo.")
    @Min(value = 1, message = "O mês de validade deve ser entre 1 e 12.")
    @Max(value = 12, message = "O mês de validade deve ser entre 1 e 12.")
    private Integer validadeMes;

    @NotNull(message = "O ano de validade não pode ser nulo.")
    private Integer validadeAno;

    @NotBlank(message = "O CVV não pode estar vazio.")
    @Pattern(regexp = "\\d{3,4}", message = "O CVV deve conter 3 ou 4 dígitos.")
    private String cvv;

    @NotBlank(message = "O nome do titular não pode estar vazio.")
    private String nomeTitular;

    @NotBlank(message = "O CPF do titular não pode estar vazio.")
    @CPF(message = "CPF inválido.")
    private String cpfTitular;

    @NotNull(message = "O tipo de cartão deve ser informado.")
    private CardType tipoCartao;

    // Validar data de expiração
    @AssertTrue(message = "A data de validade é retroativa e inválida.")
    public boolean isExpirationDateValid() {
        if (validadeMes == null || validadeAno == null)
            return true;

        LocalDate today = LocalDate.now();
        LocalDate expirationDate = LocalDate.of(validadeAno, validadeMes, 1)
                .withDayOfMonth(LocalDate.of(validadeAno, validadeMes, 1).lengthOfMonth());

        return !expirationDate.isBefore(today);
    }

    // Validação dos números do cartão de pagamento (Validação de Luhn)
    @AssertTrue(message = "O número do cartão informado é inválido.")
    public boolean isLuhnValid() {
        if (numeroCartao == null || !numeroCartao.matches("\\d+"))
            return true;

        int sum = 0;
        boolean alternate = false;

        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numeroCartao.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}