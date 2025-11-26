package br.com.foodelivery.tialudeliveryback.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "principal", nullable = false)
    private boolean principal;

    public PaymentMethod() {}

    public PaymentMethod(String id, Long userId, boolean active, boolean principal) {
        this.id = id;
        this.userId = userId;
        this.active = active;
        this.principal = principal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}