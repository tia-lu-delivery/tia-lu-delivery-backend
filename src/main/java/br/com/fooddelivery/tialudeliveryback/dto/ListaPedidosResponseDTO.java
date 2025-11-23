package br.com.fooddelivery.tialudeliveryback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ListaPedidosResponseDTO {

    private int page;
    private int size;

    @JsonProperty("total_pedidos")
    private long totalPedidos;

    private List<PedidoResumoDTO> pedidos;

    public ListaPedidosResponseDTO() {}

    public ListaPedidosResponseDTO(int page, int size, long totalPedidos, List<PedidoResumoDTO> pedidos) {
        this.page = page;
        this.size = size;
        this.totalPedidos = totalPedidos;
        this.pedidos = pedidos;
    }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public long getTotalPedidos() { return totalPedidos; }
    public void setTotalPedidos(long totalPedidos) { this.totalPedidos = totalPedidos; }

    public List<PedidoResumoDTO> getPedidos() { return pedidos; }
    public void setPedidos(List<PedidoResumoDTO> pedidos) { this.pedidos = pedidos; }
}