package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESTABELECIMENTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private boolean ativo;

}
