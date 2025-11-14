package pedido;

public class Pedido {

    private Long id;
    private Long clienteId;
    private Long restauranteId;
    private Double valorTotal;
    private String status;
    private java.time.LocalDateTime criadoEm;
    private java.time.LocalDateTime atualizadoEm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.time.LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(java.time.LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public java.time.LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(java.time.LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
