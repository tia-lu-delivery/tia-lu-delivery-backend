package br.com.fooddelivery.tialudeliveryback.dto;

public class EstabelecimentoDTO {

    private String idEstabelecimento;
    private String nomeFantasia;

    // Construtor vazio
    public EstabelecimentoDTO() {
    }

    // Construtor completo
    public EstabelecimentoDTO(String idEstabelecimento, String nomeFantasia) {
        this.idEstabelecimento = idEstabelecimento;
        this.nomeFantasia = nomeFantasia;
    }

    // Getters e Setters
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
import org.springframework.beans.BeanUtils;

public class EstabelecimentoDTO {

    private Long id;

    private String nomeFantasia;

    //---------------

    public EstabelecimentoDTO(EstabelecimentoDTO estabelecimento) {
        BeanUtils.copyProperties(estabelecimento, this);
    }
    public EstabelecimentoDTO() {}

    //--------------

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String  getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
