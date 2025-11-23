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
    private String numeroCartao;

    @NotNull(message = "O mês de validade não pode estar vazio.")
    @Min(value = 1, message = "Mês de validade inválido")
    @Max(value = 12, message = "Mês de validade inválido")
    private Integer validadeMes;

    @NotNull(message = "O ano de validade não pode estar vazio.")
    private Integer validadeAno;

    @NotBlank(message = "O CVV não pode estar vazio.")
    @Pattern(regexp = "\\d{3,4}", message = "O CVV deve conter 3 ou 4 dígitos.")
    private String cvv;

    @NotBlank(message = "O nome do titular não pode estar vazio.")
    private String nomeTitular;

    @CPF(message = "CPF inválido.")
    @NotBlank(message = "O CPF do titular não pode estar vazio.")
    private String cpfTitular;

    @NotNull(message = "O tipo de cartão deve ser informado.")
    private CardType tipoCartao;

    // Validar data de expiração
    @AssertTrue(message = "A data de validade é retroativa e inválida.")
    public boolean isExpirationDateValid() {
        if (validadeMes == null || validadeAno == null)
            return true; // deixa para @NotNull

        // Se mês fora do intervalo, deixa apenas @Min/@Max gerarem erro
        if (validadeMes < 1 || validadeMes > 12)
            return true;

        try {
            LocalDate today = LocalDate.now();
            LocalDate expirationDate = LocalDate.of(validadeAno, validadeMes, 1)
                    .withDayOfMonth(LocalDate.of(validadeAno, validadeMes, 1).lengthOfMonth());
            return !expirationDate.isBefore(today);
        } catch (Exception e) {
            // Se der erro inesperado na construção da data, considera inválido
            return false;
        }
    }

    // Validação dos números do cartão de pagamento (Validação de Luhn)
    @AssertTrue(message = "O número do cartão informado é inválido.")
    public boolean isLuhnValid() {
        if (numeroCartao == null || numeroCartao.isBlank())
            return true;

        // Limpar o número do cartão removendo espaços e caracteres especiais
        String numeroLimpo = numeroCartao.replaceAll("[^\\d]", "");

        // Se não tem apenas dígitos, deixa para outras validações
        if (!numeroLimpo.matches("\\d+") || numeroLimpo.length() < 13 || numeroLimpo.length() > 16)
            return true;

        int sum = 0;
        boolean alternate = false;

        for (int i = numeroLimpo.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numeroLimpo.substring(i, i + 1));
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

    // Validação do tamanho do número do cartão após limpeza
    @AssertTrue(message = "O número do cartão informado é inválido.")
    public boolean isCardNumberLengthValid() {
        if (numeroCartao == null || numeroCartao.isBlank())
            return true;

        String numeroLimpo = getNumeroCartaoLimpo();

        // Deve conter apenas dígitos e ter entre 13 e 16 caracteres
        return numeroLimpo.matches("\\d{13,16}");
    }

    // Limpar o número do cartão removendo espaços e caracteres especiais
    public String getNumeroCartaoLimpo() {
        if (numeroCartao == null || numeroCartao.isBlank())
            return "";
        return numeroCartao.replaceAll("[^\\d]", "");
    }
}