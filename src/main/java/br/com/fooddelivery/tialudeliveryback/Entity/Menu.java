package br.com.fooddelivery.tialudeliveryback.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Um Menu tem várias Categorias
    @OneToMany(mappedBy = "menu")
    private List<Categoria> categorias = new ArrayList<>();

    // Um Menu tem vários Produtos
    @OneToMany(mappedBy = "menu")
    private List<Produto> produtos = new ArrayList<>();

    // --- Getters e Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}