package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA para mapear a tabela TB_CATEGORIA.
 * Representa uma categoria de itens dentro de um cardápio.
 */
@Entity
@Table(name = "TB_CATEGORIA", uniqueConstraints = {
    // Garante a unicidade do nome da categoria dentro de um cardápio específico (CA 1.4)
    @UniqueConstraint(columnNames = {"id_cardapio", "nome_categoria"})
})
@Data // Gera Getters, Setters, toString, equals e hashCode (Lombok)
@Builder // Gera um construtor com o padrão Builder (Lombok)
@NoArgsConstructor // Gera um construtor sem argumentos (Lombok)
@AllArgsConstructor // Gera um construtor com todos os argumentos (Lombok)
public class Categoria {

    /**
     * Identificador único da categoria (Chave Primária).
     * Mapeado para a coluna 'id_categoria'.
     */
    @Id
    @Column(name = "id_categoria", nullable = false, length = 36)
    private String idCategoria; // Usando String para UUID, comum em JPA/Hibernate

    /**
     * Identificador do cardápio ao qual esta categoria pertence (Chave Estrangeira).
     * Mapeado para a coluna 'id_cardapio'.
     */
    @Column(name = "id_cardapio", nullable = false, length = 36)
    private String idCardapio;

    /**
     * Nome da categoria.
     * Mapeado para a coluna 'nome_categoria'.
     */
    @Column(name = "nome_categoria", nullable = false, length = 255)
    private String nomeCategoria;

    /**
     * Descrição opcional da categoria.
     * Mapeado para a coluna 'descricao'.
     */
    @Column(name = "descricao", length = 500)
    private String descricao;

    /**
     * Ordem de exibição da categoria no cardápio.
     * Mapeado para a coluna 'ordem'.
     */
    @Column(name = "ordem", nullable = false)
    private Integer ordem;

    /**
     * Status de disponibilidade/visibilidade da categoria.
     * Mapeado para a coluna 'disponivel'.
     */
    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel;}