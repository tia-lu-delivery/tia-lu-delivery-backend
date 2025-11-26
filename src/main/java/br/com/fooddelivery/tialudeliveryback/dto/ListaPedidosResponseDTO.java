package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

// CA-007: DTO de alto nível que contém a lista e os dados de paginação
public class ListaPedidosResponseDTO {
    private final int page;
    private final int size;
    private final long total_pedidos; // CA-007: Nome de campo exigido
    private final List<PedidoResumoDTO> pedidos;

    public ListaPedidosResponseDTO(int page, int size, long totalPedidos, List<PedidoResumoDTO> pedidos) {
        this.page = page;
        this.size = size;
        this.total_pedidos = totalPedidos; // Mapeado para o nome exigido
        this.pedidos = pedidos;
    }

    // Getters
    public int getPage() { return page; }
    public int getSize() { return size; }
    public long getTotal_pedidos() { return total_pedidos; }
    public List<PedidoResumoDTO> getPedidos() { return pedidos; }
}