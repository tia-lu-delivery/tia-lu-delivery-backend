package br.com.fooddelivery.tialudeliveryback.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_meio_pagamento") // Nome da tabela no banco
@Data // Cria Getters, Setters, toString, etc. automaticamente
@NoArgsConstructor // Cria construtor vazio (obrigatório pro JPA)
@AllArgsConstructor // Cria construtor com todos os argumentos
public class PaymentMethod {

    @Id
    private String id; // Ex: "MP001"

    
    @Column(name = "user_id", nullable = false)
    private String userId; // dono do cartão / carteira
    // --- DADOS SENSÍVEIS (Existem aqui, mas o Mapper NÃO passa pro DTO) ---
    
    @Column(name = "numero_cartao")
    private String numeroCartao; // Precisamos disso pra extrair os 4 últimos dígitos
    
    private String cvv; // Nunca deve sair daqui para o DTO

    // --- DADOS PÚBLICOS ---

    private String bandeira; // Ex: "Visa"

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "validade_mes")
    private Integer validadeMes;

    @Column(name = "validade_ano")
    private Integer validadeAno;

    @Column(name = "tipo_cartao")
    private String tipoCartao; // Ex: "CREDITO" ou "DEBITO"

    @Column(name = "ativo")
    private Boolean ativo;
}