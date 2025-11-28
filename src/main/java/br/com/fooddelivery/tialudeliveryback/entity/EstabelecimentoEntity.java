package br.com.fooddelivery.tialudeliveryback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "estabelecimento",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<HorarioFuncionamentoEntity> horarios = new ArrayList<>();

    /**
     * Atualiza toda a lista de horários
     * garantindo que a FK seja setada corretamente
     */
    public void setHorarios(List<HorarioFuncionamentoEntity> novosHorarios) {

        // remove horários antigos
        this.horarios.clear();

        if (novosHorarios != null) {
            novosHorarios.forEach(h -> h.setEstabelecimento(this));
            this.horarios.addAll(novosHorarios);
        }
    }
}

