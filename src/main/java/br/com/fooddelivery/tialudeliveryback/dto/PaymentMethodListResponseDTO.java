package br.com.fooddelivery.tialudeliveryback.dto;

import java.util.List;

public class PaymentMethodListResponseDTO {

    private int totalMeios;
    private List<PaymentMethodDTO> meiosPagamento;

    public PaymentMethodListResponseDTO(int totalMeios, List<PaymentMethodDTO> meiosPagamento) {
        this.totalMeios = totalMeios;
        this.meiosPagamento = meiosPagamento;
    }

    public int getTotalMeios() {
        return totalMeios;
    }

    public List<PaymentMethodDTO> getMeiosPagamento() {
        return meiosPagamento;
    }
}
