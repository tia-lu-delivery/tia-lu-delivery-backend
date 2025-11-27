package br.com.fooddelivery.tialudeliveryback.domain.wallet;

public class PaymentMethod {

    private final PaymentMethodId id;
    private boolean active;
    private boolean principal;

    public PaymentMethod(PaymentMethodId id, boolean active) {
        this.id = id;
        this.active = active;
        this.principal = false;
    }

    public PaymentMethodId getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void deactivate() {
        this.active = false;
        this.principal = false; // não pode continuar principal
    }

    public void setAsPrincipal() {
        if (!this.active) {
            throw new IllegalStateException("Método inativo não pode ser principal");
        }
        this.principal = true;
    }

    public void removePrincipal() {
        this.principal = false;
    }
}
