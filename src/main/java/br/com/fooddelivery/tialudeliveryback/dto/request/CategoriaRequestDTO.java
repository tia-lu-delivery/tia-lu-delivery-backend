package br.com.fooddelivery.tialudeliveryback.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data // Gera Getters, Setters, toString, equals, hashCode
public class CategoriaRequestDTO {
    
    // CA 1.2: Validação obrigatória para 400 Bad Request
    @NotBlank(message = "O nome da categoria deve ser preenchido.") 
    private String nomeCategoria;
    
    private String descricao; 
    
    @NotNull(message = "O status de disponibilidade é obrigatório.") 
    private Boolean disponivel; 

    // Getters e Setters...
}