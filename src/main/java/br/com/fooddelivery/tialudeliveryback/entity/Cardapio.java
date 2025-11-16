package com.fooddelivery.tialudeliveryback.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cardapio", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"nome_cardapio", "id_estabelecimento"}))
public class Cardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cardapio")
    private String idCardapio;
    
    @Column(name = "nome_cardapio", nullable = false, length = 100)
    private String nomeCardapio;
    
    @Column(name = "descricao", length = 500)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusCardapio status = StatusCardapio.RASCUNHO;
    
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estabelecimento", nullable = false)
    private Estabelecimento estabelecimento;
    
    public Cardapio() {}
    
    public Cardapio(String nomeCardapio, String descricao, Estabelecimento estabelecimento) {
        this.nomeCardapio = nomeCardapio;
        this.descricao = descricao;
        this.estabelecimento = estabelecimento;
    }
    
    public String getIdCardapio() { return idCardapio; }
    public void setIdCardapio(String idCardapio) { this.idCardapio = idCardapio; }
    
    public String getNomeCardapio() { return nomeCardapio; }
    public void setNomeCardapio(String nomeCardapio) { this.nomeCardapio = nomeCardapio; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public StatusCardapio getStatus() { return status; }
    public void setStatus(StatusCardapio status) { this.status = status; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    
    public Estabelecimento getEstabelecimento() { return estabelecimento; }
    public void setEstabelecimento(Estabelecimento estabelecimento) { this.estabelecimento = estabelecimento; }
    
    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}