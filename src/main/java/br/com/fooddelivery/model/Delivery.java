package br.com.fooddelivery.model;

public class Delivery {
    private Long id;
    private String customerName;
    private String address;
    private String status;

    // Construtores
    public Delivery() {}

    public Delivery(Long id, String customerName, String address, String status) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}