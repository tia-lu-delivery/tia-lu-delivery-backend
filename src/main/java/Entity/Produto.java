package Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private BigDecimal price;
    private String description;
    private Boolean isAvailable;

    @ManyToMany
    @JoinTable(
            name = "cardapio_produto",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "cardapio_id")
    )
    private List<Cardapio> cardapios;
}
