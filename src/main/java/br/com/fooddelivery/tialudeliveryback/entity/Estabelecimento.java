package com.fooddelivery.tialudeliveryback.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_estabelecimento")
    private String idEstabelecimento;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
    
    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cardapio> cardapios = new ArrayList<>();
    
    public Estabelecimento() {}
    
    public Estabelecimento(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }
    
    public String getIdEstabelecimento() { return idEstabelecimento; }
    public void setIdEstabelecimento(String idEstabelecimento) { this.idEstabelecimento = idEstabelecimento; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
    
    public List<Cardapio> getCardapios() { return cardapios; }
    public void setCardapios(List<Cardapio> cardapios) { this.cardapios = cardapios; }
}