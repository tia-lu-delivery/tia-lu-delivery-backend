package br.com.fooddelivery.tialudeliveryback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private String idCardapio;
    private String nomeCardapio;
    private String dataAtualizacao;
    private EstabelecimentoDTO estabelecimento;
    private List<CategoriaDTO> categorias;
    private String mensagem;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriaDTO {

        private String idCategoria;
        private String nomeCategoria;
        private int ordem;
        private Boolean disponivel;
        private List<ProdutoDTO> produtos;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProdutoDTO {

        private String idProduto;
        private String nomeProduto;
        private String descricao;
        private Double precoUnitario;
        private String imagemUrl;
        private Boolean disponivel;
        private Integer estoque;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EstabelecimentoDTO {

        private String idEstabelecimento;
        private String nomeFantasia;
    }

}
