package br.com.fooddelivery.tialudeliveryback.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects; // Re-adding the Objects import

public class UserWallet {
    private String userId;
    private List<PaymentMethod> paymentMethods;

    public UserWallet(String userId) {
        this.userId = userId;
        this.paymentMethods = new ArrayList<>();
    }

    public UserWallet(String userId, List<PaymentMethod> paymentMethods) {
        this.userId = userId;
        this.paymentMethods = paymentMethods;
    }

    public String getUserId() {
        return userId;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethods.add(paymentMethod);
    }

    public void removePaymentMethod(String paymentMethodId) {
        this.paymentMethods.removeIf(pm -> pm.getId().equals(paymentMethodId));
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWallet that = (UserWallet) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserWallet{" +
                "userId='" + userId + '\'' +
                ", paymentMethods=" + paymentMethods +
                '}';
    }
}
