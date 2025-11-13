package br.com.fooddelivery.tialudeliveryback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Establishment {

    @Id
    private String idEstabelecimento;

    @PrePersist
    public void generateId() {
        if (this.idEstabelecimento == null) {
            this.idEstabelecimento = UUID.randomUUID().toString();
        }
    }
}
