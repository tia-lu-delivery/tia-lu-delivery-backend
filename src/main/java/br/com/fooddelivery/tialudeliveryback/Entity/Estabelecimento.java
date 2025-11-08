package br.com.fooddelivery.tialudeliveryback.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String cnpj;
    private String phone;
    private String address;
    private Boolean isOpen;

    @OneToMany(mappedBy = "estabelecimento")
    private List<Cardapio> cardapios;
}
