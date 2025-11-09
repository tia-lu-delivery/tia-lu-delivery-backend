package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class MenuDTO {

    private String idCardapio;
    private String nomeCardapio;
    private String dataAtualizacao;
    private EstabelecimentoDTO estabelecimento;
    private List<CategoriaDTO> categorias;
    private String mensagem;

    public MenuDTO() {
    }

    public MenuDTO(String idCardapio, String nomeCardapio, String dataAtualizacao,
                   EstabelecimentoDTO estabelecimento, List<CategoriaDTO> categorias, String mensagem) {
        this.idCardapio = idCardapio;
        this.nomeCardapio = nomeCardapio;
        this.dataAtualizacao = dataAtualizacao;
        this.estabelecimento = estabelecimento;
        this.categorias = categorias;
        this.mensagem = mensagem;
    }

    public String getIdCardapio() {
        return idCardapio;
    }

    public void setIdCardapio(String idCardapio) {
        this.idCardapio = idCardapio;
    }

    public String getNomeCardapio() {
        return nomeCardapio;
    }

    public void setNomeCardapio(String nomeCardapio) {
        this.nomeCardapio = nomeCardapio;
    }

    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public EstabelecimentoDTO getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    // ------------------------------
    // Classe interna CategoriaDTO
    // ------------------------------
    public static class CategoriaDTO {

        private String idCategoria;
        private String nomeCategoria;
        private int ordem;
        private Boolean disponivel;
        private List<ProdutoDTO> produtos;

        public CategoriaDTO() {
        }

        public CategoriaDTO(String idCategoria, String nomeCategoria, int ordem,
                            Boolean disponivel, List<ProdutoDTO> produtos) {
            this.idCategoria = idCategoria;
            this.nomeCategoria = nomeCategoria;
            this.ordem = ordem;
            this.disponivel = disponivel;
            this.produtos = produtos;
        }

        public String getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(String idCategoria) {
            this.idCategoria = idCategoria;
        }

        public String getNomeCategoria() {
            return nomeCategoria;
        }

        public void setNomeCategoria(String nomeCategoria) {
            this.nomeCategoria = nomeCategoria;
        }

        public int getOrdem() {
            return ordem;
        }

        public void setOrdem(int ordem) {
            this.ordem = ordem;
        }

        public Boolean getDisponivel() {
            return disponivel;
        }

        public void setDisponivel(Boolean disponivel) {
            this.disponivel = disponivel;
        }

        public List<ProdutoDTO> getProdutos() {
            return produtos;
        }

        public void setProdutos(List<ProdutoDTO> produtos) {
            this.produtos = produtos;
        }
    }

    // ------------------------------
    // Classe interna ProdutoDTO
    // ------------------------------
    public static class ProdutoDTO {

        private String idProduto;
        private String nomeProduto;
        private String descricao;
        private Double precoUnitario;
        private String imagemUrl;
        private Boolean disponivel;
        private Integer estoque;

        public ProdutoDTO() {
        }

        public ProdutoDTO(String idProduto, String nomeProduto, String descricao, Double precoUnitario,
                          String imagemUrl, Boolean disponivel, Integer estoque) {
            this.idProduto = idProduto;
            this.nomeProduto = nomeProduto;
            this.descricao = descricao;
            this.precoUnitario = precoUnitario;
            this.imagemUrl = imagemUrl;
            this.disponivel = disponivel;
            this.estoque = estoque;
        }

        public String getIdProduto() {
            return idProduto;
        }

        public void setIdProduto(String idProduto) {
            this.idProduto = idProduto;
        }

        public String getNomeProduto() {
            return nomeProduto;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public Double getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(Double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        public String getImagemUrl() {
            return imagemUrl;
        }

        public void setImagemUrl(String imagemUrl) {
            this.imagemUrl = imagemUrl;
        }

        public Boolean getDisponivel() {
            return disponivel;
        }

        public void setDisponivel(Boolean disponivel) {
            this.disponivel = disponivel;
        }

        public Integer getEstoque() {
            return estoque;
        }

        public void setEstoque(Integer estoque) {
            this.estoque = estoque;
        }
    }

    // ------------------------------
    // Classe interna EstabelecimentoDTO
    // ------------------------------
    public static class EstabelecimentoDTO {

        private String idEstabelecimento;
        private String nomeFantasia;

        public EstabelecimentoDTO() {
        }

        public EstabelecimentoDTO(String idEstabelecimento, String nomeFantasia) {
            this.idEstabelecimento = idEstabelecimento;
            this.nomeFantasia = nomeFantasia;
        }

        public String getIdEstabelecimento() {
            return idEstabelecimento;
        }

        public void setIdEstabelecimento(String idEstabelecimento) {
            this.idEstabelecimento = idEstabelecimento;
        }

        public String getNomeFantasia() {
            return nomeFantasia;
        }

        public void setNomeFantasia(String nomeFantasia) {
            this.nomeFantasia = nomeFantasia;
    }
}
}