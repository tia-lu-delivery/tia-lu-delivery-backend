package br.com.fooddelivery.tialudeliveryback.entity;

import br.com.fooddelivery.tialudeliveryback.dto.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HORARIO_FUNCIONAMENTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioFuncionamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private DiaSemana dia;

    @Column(nullable = false)
    private boolean aberto;

    @Column(name = "hora_abertura")
    private String horaAbertura;

    @Column(name = "hora_fechamento")
    private String horaFechamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private EstabelecimentoEntity estabelecimento;
}
