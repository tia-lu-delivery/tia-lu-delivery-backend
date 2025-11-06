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
import static org.mockito.Mockito.when;

import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;
import br.com.fooddelivery.tialudeliveryback.exception.ProductNotFoundException;
import org.mockito.Mockito;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("PUT /api/v1/merchant/{est}/products/{prod}/enable -> 200 e JSON de sucesso")
    void enableProduct_returns200WithSuccessBody() throws Exception {
        when(productService.enableProduct("R1001", "P501")).thenReturn(
                ProductEnableResponse.builder()
                        .id_produto("P501")
                        .status("ativado")
                        .detalhe("Produto reativado e marcado como disponível.")
                        .disponivel(true)
                        .build()
        );

        mockMvc.perform(put("/api/v1/merchant/R1001/products/P501/enable"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id_produto").value("P501"))
                .andExpect(jsonPath("$.disponivel").value(true))
                .andExpect(jsonPath("$.status").value("ativado"));
    }
    
    @Test
    @DisplayName("PUT /api/v1/merchant/{est}/products/{prod}/enable -> 404 com envelope de erro")
    void enableProduct_returns404WithErrorEnvelope() throws Exception {
        when(productService.enableProduct("R404", "PX")).thenThrow(new ProductNotFoundException("PX", "R404"));

        mockMvc.perform(put("/api/v1/merchant/R404/products/PX/enable"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.erro.codigo").value("PRODUTO_NAO_ENCONTRADO"))
                .andExpect(jsonPath("$.erro.detalhe").exists());
    }
    @TestConfiguration
    static class MockConfig {
        @Bean
        @Primary
        ProductService productService() {
            return Mockito.mock(ProductService.class);
        }
    }
}
