package br.com.fooddelivery.tialudeliveryback.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_payment_method")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String tipo;

    private String numeroCartao;
    
    private String nomeCartao;
    
    private String bandeiraCartao;
    
    private String codigoVoucher;

    @Column(nullable = false)
    private Boolean ativo = true;

    private LocalDateTime criadoEm;
    
    private LocalDateTime inativadoEm;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }
}
