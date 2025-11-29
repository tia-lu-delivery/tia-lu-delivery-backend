package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "tb_menu")
@Data
public class Menu {
    
    @Id
    private UUID id; // ID manual (sem @GeneratedValue)

    @Column(name = "nome_menu", nullable = false)
    private String nomeMenu;
    
    // REMOVEMOS A LISTA DE CATEGORIAS PARA CORRIGIR O ERRO DE MAPEAMENTO
    // Como Categoria não tem mais o objeto 'cardapio', este relacionamento quebrava o JPA.
    
    // Construtor vazio (obrigatório pelo JPA)
    public Menu() {}
}