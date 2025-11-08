package br.com.fooddelivery.tialudeliveryback.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "cardapios")
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", foreignKey = @ForeignKey(name = "fk_cardapio_estabelecimento"))
    private Estabelecimento estabelecimento;
}
