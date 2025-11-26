package br.com.fooddelivery.tialudeliveryback.api.dto;

import br.com.fooddelivery.tialudeliveryback.core.domain.PaymentMethodType;

public class PaymentMethodOutputDTO {
    private String id;
    private PaymentMethodType type;
    private String lastFourDigits; // Masked card number
    private String expiryDate; // MM/YY format
    private String holderName;

    // Constructors
    public PaymentMethodOutputDTO() {
    }

    public PaymentMethodOutputDTO(String id, PaymentMethodType type, String lastFourDigits, String expiryDate, String holderName) {
        this.id = id;
        this.type = type;
        this.lastFourDigits = lastFourDigits;
        this.expiryDate = expiryDate;
        this.holderName = holderName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PaymentMethodType getType() {
        return type;
    }

    public void setType(PaymentMethodType type) {
        this.type = type;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
