package br.com.fooddelivery.tialudeliveryback.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

public class PedidoRequestDTO {

    @NotNull(message = "O IdEstabelecimento é obrigatório.")
    private Long idEstabelecimento;

    @NotNull(message = "O IdEnderecoEntrega é obrigatório.")
    private Long idEnderecoEntrega;

    @NotNull(message = "O valorTotalEnviado é obrigatório.")
    @Positive(message = "O valor total deve ser positivo.")
    private BigDecimal valorTotalEnviado;

    @NotEmpty(message = "A lista de itens não pode estar vazia.")
    @Valid

    private List<ItemRequestDTO> itens;

    private DescontoRequestDTO desconto;

    private String observacoesGerais;

    public Long getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Long idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Long getIdEnderecoEntrega() {
        return idEnderecoEntrega;
    }

    public void setIdEnderecoEntrega(Long idEnderecoEntrega) {
        this.idEnderecoEntrega = idEnderecoEntrega;
    }

    public BigDecimal getValorTotalEnviado() {
        return valorTotalEnviado;
    }

    public void setValorTotalEnviado(BigDecimal valorTotalEnviado) {
        this.valorTotalEnviado = valorTotalEnviado;
    }

    public List<ItemRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemRequestDTO> itens) {
        this.itens = itens;
    }

    public DescontoRequestDTO getDesconto() {
        return desconto;
    }

    public void setDesconto(DescontoRequestDTO desconto) {
        this.desconto = desconto;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }
}