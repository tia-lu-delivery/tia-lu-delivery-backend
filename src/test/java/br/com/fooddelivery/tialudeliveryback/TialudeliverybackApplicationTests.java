package br.com.fooddelivery.tialudeliveryback;

import br.com.fooddelivery.tialudeliveryback.config.TestAddressServiceConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestAddressServiceConfiguration.class)
class TialudeliverybackApplicationTests {

	@Test
	void contextLoads() {
	}

}
