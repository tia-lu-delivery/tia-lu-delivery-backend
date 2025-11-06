package br.com.fooddelivery.tialudeliveryback.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data // Gera Getters, Setters, toString, equals, hashCode
public class CategoriaResponseDTO {

    private String idCategoria;
    private String mensagem;
    
    @JsonProperty("dadosCategoria") // Garante o nome exato no JSON
    private CategoriaData dados; 

    // Construtor, Getters e Setters...
    
    // Classe interna para o objeto "dadosCategoria"
    @Data
    public static class CategoriaData {
        private String nomeCategoria;
        private Integer ordem; 
        private Boolean disponivel;
        
        // Construtor, Getters e Setters...
    }
}