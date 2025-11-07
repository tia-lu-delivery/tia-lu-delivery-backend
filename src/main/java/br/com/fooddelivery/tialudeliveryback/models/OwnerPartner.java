package br.com.fooddelivery.tialudeliveryback.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSocio;
    private String cpf;
    private String rg;
    private String orgaoEmissorRg;
    private String nomeCompleto;
    private String email;
    private String telefone;

}
