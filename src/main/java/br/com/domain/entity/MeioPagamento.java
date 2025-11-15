package br.com.domain.entity;

import br.com.domain.enums.TipoCartao;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

import br.com.domain.enums.Bandeira;

@SuppressWarnings("unused")

@Entity
@Table(name = "meio_pagamento")
public class MeioPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ajuste 'Usuario' para o nome real da sua classe de entidade de usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cartao", nullable = false, length = 20)
    private TipoCartao tipoCartao;

    @Enumerated(EnumType.STRING)
    @Column(name = "bandeira", length = 20)
    private Bandeira bandeira;

    @Column(name = "nome_titular", nullable = false, length = 100)
    private String nomeTitular;

    @Column(name = "cpf_titular", nullable = false, length = 14)
    private String cpfTitular;

    // Armazena o cartão criptografado
    @Column(name = "numero_cartao_encrypted", nullable = false, length = 512)
    private String numeroCartaoEncrypted;

    // Armazena apenas os 4 últimos dígitos visíveis (ex: "4444")
    @Column(name = "ultimos_digitos", nullable = false, length = 4)
    private String ultimosDigitos;

    // Armazena o CVV criptografado
    @Column(name = "cvv_encrypted", nullable = false, length = 512)
    private String cvvEncrypted;

    @Column(name = "validade_mes", nullable = false)
    private Integer validadeMes;

    @Column(name = "validade_ano", nullable = false)
    private Integer validadeAno;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public MeioPagamento() {
        // Construtor padrão para JPA
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public TipoCartao getTipoCartao() { return tipoCartao; }
    public void setTipoCartao(TipoCartao tipoCartao) { this.tipoCartao = tipoCartao; }
    public Bandeira getBandeira() { return bandeira; }
    public void setBandeira(Bandeira bandeira) { this.bandeira = bandeira; }
    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public String getCpfTitular() { return cpfTitular; }
    public void setCpfTitular(String cpfTitular) { this.cpfTitular = cpfTitular; }
    public String getNumeroCartaoEncrypted() { return numeroCartaoEncrypted; }
    public void setNumeroCartaoEncrypted(String numeroCartaoEncrypted) { this.numeroCartaoEncrypted = numeroCartaoEncrypted; }
    public String getUltimosDigitos() { return ultimosDigitos; }
    public void setUltimosDigitos(String ultimosDigitos) { this.ultimosDigitos = ultimosDigitos; }
    public String getCvvEncrypted() { return cvvEncrypted; }
    public void setCvvEncrypted(String cvvEncrypted) { this.cvvEncrypted = cvvEncrypted; }
    public Integer getValidadeMes() { return validadeMes; }
    public void setValidadeMes(Integer validadeMes) { this.validadeMes = validadeMes; }
    public Integer getValidadeAno() { return validadeAno; }
    public void setValidadeAno(Integer validadeAno) { this.validadeAno = validadeAno; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
