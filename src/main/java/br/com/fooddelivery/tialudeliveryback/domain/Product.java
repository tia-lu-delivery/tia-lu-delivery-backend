package br.com.fooddelivery.tialudeliveryback.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade de Produto (simplificada para a história DWOO-0018).
 */
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id; // id_produto

    private String merchantId; // id_estabelecimento

    private boolean disponivel;
}
