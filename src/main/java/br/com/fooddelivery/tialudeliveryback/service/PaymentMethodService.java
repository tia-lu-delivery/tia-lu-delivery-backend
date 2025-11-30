package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodDTO;
import br.com.fooddelivery.tialudeliveryback.dto.PaymentMethodListResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodService {

    public PaymentMethodListResponseDTO listarMeiosPagamento(Long idUsuario) {

        // Simulação: somente usuarios 1 e 2 têm meios cadastrados
        if (idUsuario != null && (idUsuario == 1L || idUsuario == 2L)) {

            List<PaymentMethodDTO> lista = new ArrayList<>();

            PaymentMethodDTO m1 = new PaymentMethodDTO();
            m1.setIdMeioPagamento("MP001");
            m1.setBandeiraUrl("https://img.carteira.com/visa.svg");
            m1.setUltimosDigitos("4321");
            m1.setTipoCartao("CREDITO");
            m1.setNomeTitular("Estabelecimento Exemplo LTDA");
            m1.setValidade("12/28");

            PaymentMethodDTO m2 = new PaymentMethodDTO();
            m2.setIdMeioPagamento("MP002");
            m2.setBandeiraUrl("https://img.carteira.com/mastercard.svg");
            m2.setUltimosDigitos("8765");
            m2.setTipoCartao("DEBITO");
            m2.setNomeTitular("Estabelecimento Exemplo LTDA");
            m2.setValidade("05/26");

            lista.add(m1);
            lista.add(m2);

            return new PaymentMethodListResponseDTO(lista.size(), lista);
        }

        // Para qualquer outro usuário, retorna lista vazia conforme CA-006
        return new PaymentMethodListResponseDTO(0, new ArrayList<>());
    }
}
