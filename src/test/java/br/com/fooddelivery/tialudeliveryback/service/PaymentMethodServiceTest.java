package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.model.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.repository.PaymentMethodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import({PaymentMethodService.class})
public class PaymentMethodServiceTest {

    @Autowired
    private PaymentMethodRepository repository;

    @Autowired
    private PaymentMethodService service;

    @Test
    void setAsPrincipal_success_shouldSetAndUnsetOthers() {
        PaymentMethod p1 = new PaymentMethod("MP001", "user-1", false, true);
        PaymentMethod p2 = new PaymentMethod("MP002", "user-1", true, true);
        repository.save(p1);
        repository.save(p2);

        PaymentMethod result = service.setAsPrincipal("MP001", "user-1");

        List<PaymentMethod> list = repository.findByUserId("user-1");
        Assertions.assertTrue(list.stream().anyMatch(pm -> pm.getId().equals("MP001") && pm.isPrincipal()));
        Assertions.assertTrue(list.stream().anyMatch(pm -> pm.getId().equals("MP002") && !pm.isPrincipal()));
        Assertions.assertTrue(result.isPrincipal());
    }

    @Test
    void setAsPrincipal_notFound_shouldThrow() {
        Assertions.assertThrows(PaymentMethodService.NotFoundException.class, () -> {
            service.setAsPrincipal("NOPE", "user-1");
        });
    }

    @Test
    void setAsPrincipal_inactive_shouldThrowBadRequest() {
        PaymentMethod p1 = new PaymentMethod("MP003", "user-2", false, false);
        repository.save(p1);

        Assertions.assertThrows(PaymentMethodService.BadRequestException.class, () -> {
            service.setAsPrincipal("MP003", "user-2");
        });
    }
}

