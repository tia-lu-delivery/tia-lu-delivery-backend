package br.com.fooddelivery.tialudeliveryback.DTOs;

public class DeleteCategoryResDTO {

    private String status;
    private String detalhe;

    private String idCategoriaExcluida;
    private String idCardapio;
    private ErrorDTO erro;

    public DeleteCategoryResDTO() {}

    // Sucesso
    public static DeleteCategoryResDTO sucesso(String idCardapio, String idCategoria) {
        DeleteCategoryResDTO dto = new DeleteCategoryResDTO();
        dto.status = "sucesso";
        dto.detalhe = "Categoria e todos os produtos associados foram excluídos permanentemente.";
        dto.idCardapio = idCardapio;
        dto.idCategoriaExcluida = idCategoria;
        return dto;
    }

    // Erro
    public static DeleteCategoryResDTO erro(String codigo, String detalhe) {
        DeleteCategoryResDTO dto = new DeleteCategoryResDTO();
        dto.erro = new ErrorDTO(codigo, detalhe);
        return dto;
    }

    public String getStatus() {
        return status;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public String getIdCategoriaExcluida() {
        return idCategoriaExcluida;
    }

    public String getIdCardapio() {
        return idCardapio;
    }

    public ErrorDTO getErro() {
        return erro;
    }

public static class ErrorDTO {
        private String codigo;
        private String detalhe;

        public ErrorDTO(String codigo, String detalhe) {
            this.codigo = codigo;
            this.detalhe = detalhe;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getDetalhe() {
            return detalhe;
        }
    }
}

