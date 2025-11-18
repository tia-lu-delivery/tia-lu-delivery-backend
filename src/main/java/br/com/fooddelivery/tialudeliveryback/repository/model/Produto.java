package br.com.fooddelivery.tialudeliveryback.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Representa um produto no banco de dados
@Entity
public class Produto {

    // ID único do produto (gerado automaticamente)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    // Indica se o produto está disponível para venda
    private boolean disponivel; 

    // Construtor vazio (necessário para o JPA)
    public Produto() {
    }

    // Construtor para criar um novo produto
    public Produto(String nome, boolean disponivel) {
        this.nome = nome;
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
