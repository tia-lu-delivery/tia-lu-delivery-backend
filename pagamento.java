package com.fooddelivery.domain.pagamento.entity;

import jakarta.persistence.*; 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// SIMULAÇÕES DE ENTIDADES (verificar o pacote correto destas classes)
// Elas são necessárias para as anotações @OneToOne e @ManyToOne
class Pedido { @Id private UUID id; }
class MeioPagamento { @Id private UUID id; }

// ENUM: StatusPagamento (APROVADO, REJEITADO, ERRO)

@Entity
@Table(name = "pagamento")
public class Pagamento {

    // ATRIBUTOS (CAMPOS DA TABELA)
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPagamento;

    @OneToOne 
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne 
    @JoinColumn(name = "id_meio_pagamento", nullable = false)
    private MeioPagamento meioPagamento;

    @Column(name = "id_transacao", nullable = false)
    private String idTransacao;

    @Column(name = "valor_total_cobrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotalCobrado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento; 

    @Column(name = "codigo_rejeicao")
    private String codigoRejeicao; 

    @Column(name = "data_pagamento", nullable = false)
    private LocalDateTime dataPagamento;

    @Column(name = "parcelas", nullable = false)
    private Integer parcelas = 1;

    @Column(name = "detalhes_meio_pagamento")
    private String detalhesMeioPagamento; 

    @Column(name = "erro_envio_estabelecimento", nullable = false)
    private Boolean erroEnvioEstabelecimento = false;

    //CONSTRUTOR

    public Pagamento() {

    }

    //GETTERS E SETTERS

    public UUID getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(UUID idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(MeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public BigDecimal getValorTotalCobrado() {
        return valorTotalCobrado;
    }

    public void setValorTotalCobrado(BigDecimal valorTotalCobrado) {
        this.valorTotalCobrado = valorTotalCobrado;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getCodigoRejeicao() {
        return codigoRejeicao;
    }

    public void setCodigoRejeicao(String codigoRejeicao) {
        this.codigoRejeicao = codigoRejeicao;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public String getDetalhesMeioPagamento() {
        return detalhesMeioPagamento;
    }

    public void setDetalhesMeioPagamento(String detalhesMeioPagamento) {
        this.detalhesMeioPagamento = detalhesMeioPagamento;
    }

    public Boolean getErroEnvioEstabelecimento() {
        return erroEnvioEstabelecimento;
    }

    public void setErroEnvioEstabelecimento(Boolean erroEnvioEstabelecimento) {
        this.erroEnvioEstabelecimento = erroEnvioEstabelecimento;
    }
}