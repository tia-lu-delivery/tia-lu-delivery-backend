package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.domain.Address;
import br.com.fooddelivery.tialudeliveryback.dto.AddressUpdateRequest;
import br.com.fooddelivery.tialudeliveryback.exception.AddressNotFoundException;
import br.com.fooddelivery.tialudeliveryback.repository.AddressRepository;
import br.com.fooddelivery.tialudeliveryback.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    AddressRepository repository;

    @InjectMocks
    AddressServiceImpl service;

    @Test
    void shouldThrowWhenAddressNotFound() {
        when(repository.findByUserIdAndId("U1", 10L)).thenReturn(Optional.empty());
        AddressUpdateRequest req = new AddressUpdateRequest();
        assertThrows(AddressNotFoundException.class, () -> service.updateAddress(10L, req, "U1"));
    }

    @Test
    void shouldClearDefaultWhenPadraoTrue() {
        Address address = new Address();
        address.setId(1L);
        address.setUserId("U1");
        address.setPadraoEntrega(false);
        when(repository.findByUserIdAndId("U1", 1L)).thenReturn(Optional.of(address));
        when(repository.save(address)).thenReturn(address);

        AddressUpdateRequest req = new AddressUpdateRequest();
        req.setCep("01002-000");
        req.setTipo_logradouro("Rua");
        req.setLogradouro("Teste");
        req.setNumero("1");
        req.setBairro("Centro");
        req.setCidade("SP");
        req.setEstado("SP");
        req.setTipo("Residencial");
        req.setPadrao_entrega(true);

        service.updateAddress(1L, req, "U1");
        verify(repository).clearDefaultForUser("U1");
        verify(repository).save(address);
        assertTrue(address.isPadraoEntrega());
    }
}
