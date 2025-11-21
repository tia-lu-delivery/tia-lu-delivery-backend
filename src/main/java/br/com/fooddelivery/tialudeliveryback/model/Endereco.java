// Pacote: br.com.fooddelivery.tialudeliveryback.model
package br.com.fooddelivery.tialudeliveryback.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa os dados de endereço do estabelecimento.
 * Mapeada como @Embeddable para ser incorporada na tabela 'tb_merchant'.
 * (Atende ao AC 1.5 - Validação de Endereço)
 */
@Embeddable
@Getter
@Setter
public class Endereco {

    /**
     * CEP (AC 1.5)
     * - nullable = false (Obrigatório)
     * - O formato numérico de 8 dígitos é validado na camada de DTO.
     */
    @Column(name = "end_cep", nullable = false, length = 8)
    private String cep;

    /**
     * Logradouro (Rua, Avenida, etc.) (AC 1.5)
     * - nullable = false (Obrigatório)
     */
    @Column(name = "end_logradouro", nullable = false)
    private String logradouro;

    /**
     * Número do endereço (AC 1.5)
     * - nullable = false (Obrigatório)
     */
    @Column(name = "end_numero", nullable = false)
    private String numero;

    /**
     * Complemento (Apartamento, Bloco, etc.)
     * - Opcional (não listado como obrigatório no AC 1.5)
     * - nullable = true (Padrão)
     */
    @Column(name = "end_complemento")
    private String complemento;

    /**
     * Bairro (AC 1.5)
     * - nullable = false (Obrigatório)
     */
    @Column(name = "end_bairro", nullable = false)
    private String bairro;

    /**
     * Cidade (AC 1.5)
     * - nullable = false (Obrigatório)
     */
    @Column(name = "end_cidade", nullable = false)
    private String cidade;

    /**
     * Estado (UF) (AC 1.5)
     * - nullable = false (Obrigatório)
     * - Geralmente armazenamos a sigla (ex: SP, RJ) com length = 2
     */
    @Column(name = "end_estado", nullable = false, length = 2)
    private String estado;
}