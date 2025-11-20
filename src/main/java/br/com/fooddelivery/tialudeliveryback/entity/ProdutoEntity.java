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
    @GeneratedValue(generator = "uuid")
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private Double precoUnitario;

    @Column
    private String imagemUrl;

    @Column(nullable = false)
    @Builder.Default
    private Boolean disponivel = true;

    @Column
    private Integer estoque;

    @Column
    private Integer ordem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

}
