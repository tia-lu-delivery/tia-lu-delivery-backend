package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class CardapioDTO {

    private String idCardapio;
    private String nomeCardapio;
    private String dataAtualizacao;
    private EstabelecimentoDTO estabelecimento;
    private List<CategoriaDTO> categorias;
    private String mensagem;

    // Construtor vazio
    public CardapioDTO() {
    }

    // Construtor completo
    public CardapioDTO(String idCardapio, String nomeCardapio, String dataAtualizacao,
                       EstabelecimentoDTO estabelecimento, List<CategoriaDTO> categorias, String mensagem) {
        this.idCardapio = idCardapio;
        this.nomeCardapio = nomeCardapio;
        this.dataAtualizacao = dataAtualizacao;
        this.estabelecimento = estabelecimento;
        this.categorias = categorias;
        this.mensagem = mensagem;
    }

    // Getters e Setters

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
}
