package br.com.fooddelivery.tialudeliveryback.infra.repository.impl;

import br.com.fooddelivery.tialudeliveryback.core.domain.UserWallet;
import br.com.fooddelivery.tialudeliveryback.core.repository.IPaymentMethodRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPaymentMethodRepository implements IPaymentMethodRepository {

    private final Map<String, UserWallet> storage = new HashMap<>();

    @Override
    public UserWallet findByUser(String userId) {
        return Optional.ofNullable(storage.get(userId)).orElse(null);
    }

    @Override
    public void save(UserWallet userWallet) {
        storage.put(userWallet.getUserId(), userWallet);
    }
}
