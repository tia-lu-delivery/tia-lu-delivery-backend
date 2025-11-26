package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.model.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.repository.PaymentMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MockService implements CommandLineRunner {

    private final PaymentMethodRepository repository;
    private final PaymentMethodService service;

    public MockService(PaymentMethodRepository repository, PaymentMethodService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed a couple of payment methods for a fake user "user-1"
        PaymentMethod p1 = new PaymentMethod("MP001", "user-1", false, true);
        PaymentMethod p2 = new PaymentMethod("MP002", "user-1", true, true);
        repository.save(p1);
        repository.save(p2);

        System.out.println("Before: ");
        repository.findByUserId("user-1").forEach(pm -> System.out.println(pm.getId() + " principal=" + pm.isPrincipal()));

        // set MP001 as principal
        PaymentMethod result = service.setAsPrincipal("MP001", "user-1");

        System.out.println("After set MP001 as principal: ");
        repository.findByUserId("user-1").forEach(pm -> System.out.println(pm.getId() + " principal=" + pm.isPrincipal()));

        System.out.println("Result id=" + result.getId() + " principal=" + result.isPrincipal());
    }
}

