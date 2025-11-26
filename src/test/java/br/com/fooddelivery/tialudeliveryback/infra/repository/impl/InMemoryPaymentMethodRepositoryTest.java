package br.com.fooddelivery.tialudeliveryback.infra.repository.impl;

import br.com.fooddelivery.tialudeliveryback.core.domain.PaymentMethod;
import br.com.fooddelivery.tialudeliveryback.core.domain.PaymentMethodType;
import br.com.fooddelivery.tialudeliveryback.core.domain.UserWallet;
import br.com.fooddelivery.tialudeliveryback.core.repository.IPaymentMethodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPaymentMethodRepositoryTest {

    private IPaymentMethodRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPaymentMethodRepository();
    }

    @Test
    @DisplayName("Should save a new user wallet")
    void shouldSaveNewUserWallet() {
        String userId = "user123";
        PaymentMethod pm1 = new PaymentMethod(userId, PaymentMethodType.CREDIT_CARD, "1234", "12/25", "John Doe");
        UserWallet userWallet = new UserWallet(userId, Collections.singletonList(pm1));

        repository.save(userWallet);

        UserWallet foundWallet = repository.findByUser(userId);
        assertNotNull(foundWallet);
        assertEquals(userId, foundWallet.getUserId());
        assertEquals(1, foundWallet.getPaymentMethods().size());
        assertEquals(pm1, foundWallet.getPaymentMethods().get(0));
    }

    @Test
    @DisplayName("Should update an existing user wallet")
    void shouldUpdateExistingUserWallet() {
        String userId = "user456";
        PaymentMethod pm1 = new PaymentMethod(userId, PaymentMethodType.DEBIT_CARD, "5678", "01/26", "Jane Doe");
        UserWallet userWallet = new UserWallet(userId, Collections.singletonList(pm1));
        repository.save(userWallet);

        PaymentMethod pm2 = new PaymentMethod(userId, PaymentMethodType.PIX, "pixkey", "N/A", "Jane Doe");
        userWallet.addPaymentMethod(pm2);
        repository.save(userWallet);

        UserWallet foundWallet = repository.findByUser(userId);
        assertNotNull(foundWallet);
        assertEquals(2, foundWallet.getPaymentMethods().size());
        assertTrue(foundWallet.getPaymentMethods().contains(pm1));
        assertTrue(foundWallet.getPaymentMethods().contains(pm2));
    }

    @Test
    @DisplayName("Should return null if user wallet not found")
    void shouldReturnNullIfUserWalletNotFound() {
        String userId = "nonExistentUser";
        UserWallet foundWallet = repository.findByUser(userId);
        assertNull(foundWallet);
    }

    @Test
    @DisplayName("Should handle empty payment methods list correctly")
    void shouldHandleEmptyPaymentMethodsList() {
        String userId = "user789";
        UserWallet userWallet = new UserWallet(userId, Collections.emptyList());
        repository.save(userWallet);

        UserWallet foundWallet = repository.findByUser(userId);
        assertNotNull(foundWallet);
        assertTrue(foundWallet.getPaymentMethods().isEmpty());
    }

    @Test
    @DisplayName("Should remove payment method from wallet")
    void shouldRemovePaymentMethodFromWallet() {
        String userId = "user001";
        PaymentMethod pm1 = new PaymentMethod(userId, PaymentMethodType.CREDIT_CARD, "1111", "03/27", "Alice");
        PaymentMethod pm2 = new PaymentMethod(userId, PaymentMethodType.DEBIT_CARD, "2222", "04/28", "Alice");
        UserWallet userWallet = new UserWallet(userId, List.of(pm1, pm2));
        repository.save(userWallet);

        userWallet.removePaymentMethod(pm1.getId());
        repository.save(userWallet);

        UserWallet foundWallet = repository.findByUser(userId);
        assertNotNull(foundWallet);
        assertEquals(1, foundWallet.getPaymentMethods().size());
        assertFalse(foundWallet.getPaymentMethods().contains(pm1));
        assertTrue(foundWallet.getPaymentMethods().contains(pm2));
    }
}
