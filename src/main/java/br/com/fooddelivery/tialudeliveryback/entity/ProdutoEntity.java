package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeProduto;

    @Lob
    private String descricao;

    private Double precoUnitario;

    private String imagemUrl;

    private Boolean disponivel;

    private Integer estoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

}
