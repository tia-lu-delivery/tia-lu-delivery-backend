package br.com.fooddelivery.tialudeliveryback;

import br.com.fooddelivery.tialudeliveryback.domain.entity.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.domain.repository.PaymentMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TialudeliverybackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TialudeliverybackApplication.class, args);
    }

    /**
     * Seed inicial para cadastrar um meio de pagamento de exemplo:
     *
     * {
     *   "id_meio_pagamento": "MP001",
     *   "bandeira": "Visa",
     *   "bandeira_url": "https://img.carteira.com/visa.svg",
     *   "ultimos_digitos": "4321",
     *   "nome_titular": "JOAO DA SILVA",
     *   "validade_mes": 12,
     *   "validade_ano": 2028,
     *   "tipo_cartao": "CREDITO",
     *   "status_ativo": true
     * }
     */
    @Bean
    CommandLineRunner loadData(PaymentMethodRepository paymentMethodRepository) {
        return args -> {

            // Evita duplicar caso já exista
            if (paymentMethodRepository.existsById("MP001")) {
                return;
            }

            PaymentMethod pm = new PaymentMethod();

            // ID usado pelo endpoint: /meios-pagamento/MP001
            pm.setId("MP001");

            // Dono do cartão (deve bater com o X-User-Id usado na requisição)
            pm.setUserId("USER123");

            // Número completo do cartão (para extrair os últimos 4 dígitos "4321")
            pm.setNumeroCartao("4111111111114321");

            // Demais campos conforme o contrato
            pm.setCvv("123");
            pm.setBandeira("Visa");
            pm.setNomeTitular("JOAO DA SILVA");
            pm.setValidadeMes(12);
            pm.setValidadeAno(2028);
            pm.setTipoCartao("CREDITO");
            pm.setAtivo(true);

            paymentMethodRepository.save(pm);
        };
    }
}
