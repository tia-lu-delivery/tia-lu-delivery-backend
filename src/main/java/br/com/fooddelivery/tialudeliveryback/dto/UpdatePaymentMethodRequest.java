package br.com.fooddelivery.tialudeliveryback.dto;

import javax.validation.constraints.*;

public class UpdatePaymentMethodRequest {

    @NotBlank(message = "numeroCartao é obrigatório")
    @Size(min = 13, max = 19, message = "O número do cartão deve ter entre 13 e 19 dígitos")
    private String numeroCartao;

    @NotNull(message = "validadeMes é obrigatório")
    @Min(value = 1, message = "O mês deve ser entre 1 e 12")
    @Max(value = 12, message = "O mês deve ser entre 1 e 12")
    private Integer validadeMes;

    @NotNull(message = "validadeAno é obrigatório")
    @Min(value = 2024, message = "O ano informado é inválido")
    private Integer validadeAno;

    @NotBlank(message = "cvv é obrigatório")
    @Pattern(regexp = "^[0-9]{3,4}$", message = "O CVV deve ter 3 ou 4 dígitos")
    private String cvv;

    @NotBlank(message = "nomeTitular é obrigatório")
    private String nomeTitular;
@NotBlank(message = "cpfTitular é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve ter 11 dígitos numéricos")
    private String cpfTitular;

    @NotBlank(message = "tipoCartao é obrigatório")
    private String tipoCartao; // validado posteriormente contra ENUM

    public UpdatePaymentMethodRequest() {}

    // Getters e Setters
    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public Integer getValidadeMes() { return validadeMes; }
    public void setValidadeMes(Integer validadeMes) { this.validadeMes = validadeMes; }

    public Integer getValidadeAno() { return validadeAno; }
    public void setValidadeAno(Integer validadeAno) { this.validadeAno = validadeAno; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }

    public String getCpfTitular() { return cpfTitular; }
    public void setCpfTitular(String cpfTitular) { this.cpfTitular = cpfTitular; }

    public String getTipoCartao() { return tipoCartao; }
    public void setTipoCartao(String tipoCartao) { this.tipoCartao = tipoCartao; }
}
