package br.com.fooddelivery.tialudeliveryback.api.dto;

import java.util.List;

public class UserWalletOutputDTO {
    private String userId;
    private List<PaymentMethodOutputDTO> paymentMethods;

    // Constructors
    public UserWalletOutputDTO() {
    }

    public UserWalletOutputDTO(String userId, List<PaymentMethodOutputDTO> paymentMethods) {
        this.userId = userId;
        this.paymentMethods = paymentMethods;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<PaymentMethodOutputDTO> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodOutputDTO> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
