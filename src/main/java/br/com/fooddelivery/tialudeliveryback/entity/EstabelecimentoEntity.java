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
    @GeneratedValue(generator = "uuid")
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private boolean ativo;

}
