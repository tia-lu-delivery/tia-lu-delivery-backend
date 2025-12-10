package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "CARDAPIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardapioEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String nomeCardapio;

    private String dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", referencedColumnName = "id")
    private EstabelecimentoEntity estabelecimento;

    @OneToMany(mappedBy = "cardapio", fetch = FetchType.LAZY)
    private List<CategoriaEntity> categorias;

}