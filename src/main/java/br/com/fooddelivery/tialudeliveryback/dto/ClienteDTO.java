package br.com.fooddelivery.tialudeliveryback.dto;

public class ClienteDTO {

    private String nome;
    private String numero_cliente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero_cliente() {
        return numero_cliente;
    }

    public void setNumero_cliente(String numero_cliente) {
        this.numero_cliente = numero_cliente;
    }
}