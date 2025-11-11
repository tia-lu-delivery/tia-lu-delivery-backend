package br.com.fooddelivery.tialudeliveryback.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fooddelivery.tialudeliveryback.service.ProductService;
import org.mockito.Mockito;
import br.com.fooddelivery.tialudeliveryback.security.XMerchantIdFilter;

@WebMvcTest(ProductController.class)
class ProductControllerUnauthorizedTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("PUT .../enable -> 401 e envelope NAO_AUTORIZADO quando não autorizado")
    void enableProduct_returns401WithErrorEnvelopeWhenUnauthorized() throws Exception {
        // Não envia o header X-Merchant-Id -> deve resultar em 401 pelo controller
        mockMvc.perform(put("/api/v1/merchant/R1001/products/P501/enable"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro.codigo").value("NAO_AUTORIZADO"))
                .andExpect(jsonPath("$.erro.detalhe").exists());
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        @Primary
        ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
        @Bean
        XMerchantIdFilter xMerchantIdFilter() {
            return new XMerchantIdFilter();
        }
    }
}
