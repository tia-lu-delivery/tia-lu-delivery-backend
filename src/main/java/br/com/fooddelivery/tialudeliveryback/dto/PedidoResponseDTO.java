package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class PedidoResponseDTO {

    private String numero_pedido;
    private String horario_abertura;
    private String status_pedido;

    private ClienteDTO cliente;
    private EnderecoEntregaDTO endereco_entrega;

    private List<ItemPedidoDTO> itens_pedido;

    private Double valor_total_itens;
    private Double taxa_entrega;
    private Double valor_final;

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public String getHorario_abertura() {
        return horario_abertura;
    }

    public void setHorario_abertura(String horario_abertura) {
        this.horario_abertura = horario_abertura;
    }

    public String getStatus_pedido() {
        return status_pedido;
    }

    public void setStatus_pedido(String status_pedido) {
        this.status_pedido = status_pedido;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public EnderecoEntregaDTO getEndereco_entrega() {
        return endereco_entrega;
    }

    public void setEndereco_entrega(EnderecoEntregaDTO endereco_entrega) {
        this.endereco_entrega = endereco_entrega;
    }

    public List<ItemPedidoDTO> getItens_pedido() {
        return itens_pedido;
    }

    public void setItens_pedido(List<ItemPedidoDTO> itens_pedido) {
        this.itens_pedido = itens_pedido;
    }

    public Double getValor_total_itens() {
        return valor_total_itens;
    }

    public void setValor_total_itens(Double valor_total_itens) {
        this.valor_total_itens = valor_total_itens;
    }

    public Double getTaxa_entrega() {
        return taxa_entrega;
    }

    public void setTaxa_entrega(Double taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }

    public Double getValor_final() {
        return valor_final;
    }

    public void setValor_final(Double valor_final) {
        this.valor_final = valor_final;
    }
}