package br.com.fooddelivery.tialudeliveryback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.fooddelivery.tialudeliveryback.repository")
public class TialudeliverybackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TialudeliverybackApplication.class, args);
	}

}