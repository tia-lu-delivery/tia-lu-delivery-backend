package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CARDAPIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCardapio;

    private String dataAtualizacao;

    private String mensagem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estabelecimento_id", referencedColumnName = "id")
    private EstabelecimentoEntity estabelecimento;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL)
    private List<CategoriaEntity> categorias;

}