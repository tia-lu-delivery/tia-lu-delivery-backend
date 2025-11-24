package br.com.fooddelivery.tialudeliveryback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * ⚠️ CONFIGURAÇÃO TEMPORÁRIA DE TESTE ⚠️
 *
 * O parâmetro (exclude = {DataSourceAutoConfiguration.class}) abaixo
 * serve apenas para rodar o projeto SEM Banco de Dados configurado.
 *
 * AÇÃO FUTURA: REMOVER o conteúdo entre parenteses quando
 * o Banco de Dados for configurado oficialmente.
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class TialudeliverybackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TialudeliverybackApplication.class, args);
	}
}