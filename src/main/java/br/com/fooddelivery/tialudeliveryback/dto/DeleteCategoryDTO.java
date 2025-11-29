package br.com.fooddelivery.tialudeliveryback.dto;


public class DeleteCategoryDTO {

    private String idCardapio;
    private String idCategoria;

    public DeleteCategoryDTO() {}

    public DeleteCategoryDTO(String idCardapio, String idCategoria) {
        this.idCardapio = idCardapio;
        this.idCategoria = idCategoria;
    }

    public String getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(String idCardapio) {
        this.idCardapio = idCardapio;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
