// Pacote: br.com.fooddelivery.tialudeliveryback.model
package br.com.fooddelivery.tialudeliveryback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

/**
 * Entidade principal que mapeia a tabela 'tb_merchant'.
 * Contém os dados cadastrais do estabelecimento (AC 1.1)
 */
@Entity
@Table(name = "tb_merchant") // Define o nome da tabela exatamente como na task
@Getter
@Setter
public class Merchant {

    /**
     * Chave primária (ID) do estabelecimento.
     * (Baseado no AC 1.1, que retorna um "idEstabelecimento")
     * Usamos UUID para gerar um identificador único (ex: "a1b2c3d4e5f6g7h8").
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_estabelecimento")
    private UUID idEstabelecimento;

    /**
     * CNPJ (AC 1.2, 1.3, 1.4)
     * - nullable = false (Obrigatório)
     * - length = 14 (Tamanho fixo)
     * - unique = true (Não pode repetir, AC 1.3)
     */
    @Column(name = "cnpj", nullable = false, length = 14, unique = true)
    private String cnpj;

    /**
     * Razão Social (AC 1.4)
     * - nullable = false (Obrigatório)
     */
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    /**
     * Nome Fantasia (AC 1.4)
     * - nullable = false (Obrigatório)
     */
    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    /**
     * Inscrição Estadual (AC 1.6)
     * - Opcional, portanto nullable = true (padrão)
     */
    @Column(name = "ie")
    private String ie;

    /**
     * Endereço (AC 1.4, 1.5)
     * - @Embedded informa ao JPA para "achatar" os campos da classe Endereco
     * e colocá-los como colunas aqui, na 'tb_merchant'.
     */
    @Embedded
    private Endereco endereco;

    // Construtores, Getters e Setters são gerenciados pelo Lombok
    // (@Getter, @Setter)
}