package br.com.fooddelivery.tialudeliveryback.core.domain;

import java.util.Objects;
import java.util.UUID;

public class PaymentMethod {
    private String id;
    private String userId;
    private PaymentMethodType type;
    private String cardNumber; // stored as last four digits or masked
    private String expiryDate; // MM/YY format
    private String holderName;

    public PaymentMethod(String userId, PaymentMethodType type, String cardNumber, String expiryDate, String holderName) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.holderName = holderName;
    }

    public PaymentMethod(String id, String userId, PaymentMethodType type, String cardNumber, String expiryDate, String holderName) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.holderName = holderName;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public PaymentMethodType getType() {
        return type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getHolderName() {
        return holderName;
    }

    // Setters (for immutability, consider creating new objects with changes)
    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setType(PaymentMethodType type) {
        this.type = type;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", holderName='" + holderName + '\'' +
                '}';
    }
}
