package br.com.fooddelivery.tialudeliveryback.controller;

import br.com.fooddelivery.tialudeliveryback.domain.Address;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateRequest;
import br.com.fooddelivery.tialudeliveryback.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AddressControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AddressRepository repository;
    @Autowired
    ObjectMapper objectMapper;

    Address address;

    @BeforeEach
    void setup() {
        address = new Address();
        address.setUserId("U1");
        address.setCep("01001-000");
        address.setTipoLogradouro("Rua");
        address.setLogradouro("Antiga");
        address.setNumero("10");
        address.setBairro("Centro");
        address.setCidade("SP");
        address.setEstado("SP");
        address.setComplemento("Apto 1");
        address.setTipo("Residencial");
        address.setPadraoEntrega(false);
        repository.save(address);
    }

    @Test
    void shouldReturn200AndUpdateAddress() throws Exception {
        AddressUpdateRequest req = new AddressUpdateRequest();
        req.setCep("01002-000");
        req.setTipo_logradouro("Rua");
        req.setLogradouro("Nova Direita");
        req.setNumero("100A");
        req.setBairro("Sé");
        req.setCidade("São Paulo");
        req.setEstado("SP");
        req.setComplemento("Bloco B, Sala 5");
        req.setTipo("Comercial");
        req.setPadrao_entrega(true);
        mockMvc.perform(put("/api/v1/users/address/" + address.getId())
                .header("X-User-Id", "U1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_endereco", is(address.getId().intValue())))
                .andExpect(jsonPath("$.status", is("atualizado")))
                .andExpect(jsonPath("$.endereco_atualizado.cep", is("01002-000")))
                .andExpect(jsonPath("$.endereco_atualizado.padrao_entrega", is(true)));
    }

    @Test
    void shouldReturn400ForInvalidCepAndNumero() throws Exception {
        AddressUpdateRequest req = new AddressUpdateRequest();
        req.setCep("01002000"); // inválido
        req.setTipo_logradouro("Rua");
        req.setLogradouro("Nova Direita");
        req.setNumero(""); // obrigatório
        req.setBairro("Sé");
        req.setCidade("São Paulo");
        req.setEstado("SP");
        req.setComplemento("Bloco B, Sala 5");
        req.setTipo("Comercial");
        req.setPadrao_entrega(true);
        mockMvc.perform(put("/api/v1/users/address/" + address.getId())
                .header("X-User-Id", "U1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro.codigo", is("ERRO_VALIDACAO")))
                .andExpect(jsonPath("$.erro.campos_com_erro", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$.erro.campos_com_erro[*].campo", hasItems("cep", "numero")));
    }

    @Test
    void shouldReturn404ForAddressNotFound() throws Exception {
        AddressUpdateRequest req = new AddressUpdateRequest();
        req.setCep("01002-000");
        req.setTipo_logradouro("Rua");
        req.setLogradouro("Nova Direita");
        req.setNumero("100A");
        req.setBairro("Sé");
        req.setCidade("São Paulo");
        req.setEstado("SP");
        req.setComplemento("Bloco B, Sala 5");
        req.setTipo("Comercial");
        req.setPadrao_entrega(true);
        mockMvc.perform(put("/api/v1/users/address/99999")
                .header("X-User-Id", "U1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro.codigo", is("ENDERECO_NAO_ENCONTRADO")));
    }

    @Test
    void shouldReturn401IfNoUserIdHeader() throws Exception {
        AddressUpdateRequest req = new AddressUpdateRequest();
        req.setCep("01002-000");
        req.setTipo_logradouro("Rua");
        req.setLogradouro("Nova Direita");
        req.setNumero("100A");
        req.setBairro("Sé");
        req.setCidade("São Paulo");
        req.setEstado("SP");
        req.setComplemento("Bloco B, Sala 5");
        req.setTipo("Comercial");
        req.setPadrao_entrega(true);
        mockMvc.perform(put("/api/v1/users/address/" + address.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.erro.codigo", is("NAO_AUTORIZADO")))
                .andExpect(jsonPath("$.erro.detalhe", containsString("Token de autenticação ausente")));
    }
}
