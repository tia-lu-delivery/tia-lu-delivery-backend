package br.com.fooddelivery.tialudeliveryback.config;

import br.com.fooddelivery.tialudeliveryback.models.Address;
import br.com.fooddelivery.tialudeliveryback.repositories.AddressRepository;
import br.com.fooddelivery.tialudeliveryback.services.AddressService;
import br.com.fooddelivery.tialudeliveryback.services.impl.AddressServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.mock;

/**
 * Configuração de teste para injetar dependências mock
 * durante os testes automatizados
 */
@TestConfiguration
@Profile("test")
public class TestAddressServiceConfiguration {

    /**
     * Fornece um mock do AddressRepository para testes
     */
    @Bean
    public AddressRepository addressRepository() {
        return mock(AddressRepository.class);
    }

    /**
     * Fornece a implementação real da AddressService com repositório mock
     */
    @Bean
    @Primary
    public AddressService addressService(AddressRepository addressRepository) {
        return new AddressServiceImpl(addressRepository);
    }
}

