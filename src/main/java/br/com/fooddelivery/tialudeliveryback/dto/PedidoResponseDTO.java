package br.com.fooddelivery.tialudeliveryback.dto;

public class PedidoResponseDTO {

    private Long idPedido;
    private String status;

    public PedidoResponseDTO(Long idPedido, String status) {
        this.idPedido = idPedido;
        this.status = status;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}