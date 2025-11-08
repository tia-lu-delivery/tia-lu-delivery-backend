package br.com.fooddelivery.tialudeliveryback.service;

import org.springframework.stereotype.Service;

@Service
public interface MerchantService {
    boolean existsById(String idEstabelecimento);
}