package br.com.fooddelivery.tialudeliveryback.core.repository;

import br.com.fooddelivery.tialudeliveryback.core.domain.UserWallet;

public interface IPaymentMethodRepository {
    UserWallet findByUser(String userId);
    void save(UserWallet userWallet);
}
