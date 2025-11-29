package com.tialu.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "tb_cardapio")
@Data
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "merchant_id")
    private Long merchantId; // Vínculo com o estabelecimento

    // CA-004: Configuração CRÍTICA de Cascata
    // CascadeType.ALL: Propaga persistência, merge e remove.
    // orphanRemoval = true: Se remover da lista, apaga do banco.
    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> categorias;
}

package com.tialu.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "tb_categoria")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    // A cascata continua: Deletar Categoria -> Deleta Produtos
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;
}