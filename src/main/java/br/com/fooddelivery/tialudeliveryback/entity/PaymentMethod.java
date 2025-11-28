package br.com.fooddelivery.tialudeliveryback.entity;

import br.com.fooddelivery.tialudeliveryback.enum_.TipoCartao;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String numeroTokenizado;

    @Column(nullable = false)
    private String cvvTokenizado;

    @Column(nullable = false)
    private Integer validadeMes;

    @Column(nullable = false)
    private Integer validadeAno;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private String cpfTitular;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCartao tipoCartao;

    private String bandeira;

    public PaymentMethod() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getNumeroTokenizado() { return numeroTokenizado; }
    public void setNumeroTokenizado(String numeroTokenizado) { this.numeroTokenizado = numeroTokenizado; }

    public String getCvvTokenizado() { return cvvTokenizado; }
    public void setCvvTokenizado(String cvvTokenizado) { this.cvvTokenizado = cvvTokenizado; }

    public Integer getValidadeMes() { return validadeMes; }
    public void setValidadeMes(Integer validadeMes) { this.validadeMes = validadeMes; }

    public Integer getValidadeAno() { return validadeAno; }
    public void setValidadeAno(Integer validadeAno) { this.validadeAno = validadeAno; }

    public String getNomeTitular() { return nomeTitular; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }

    public String getCpfTitular() { return cpfTitular; }
    public void setCpfTitular(String cpfTitular) { this.cpfTitular = cpfTitular; }

    public TipoCartao getTipoCartao() { return tipoCartao; }
    public void setTipoCartao(TipoCartao tipoCartao) { this.tipoCartao = tipoCartao; }

    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
}
