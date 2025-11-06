package br.com.fooddelivery.tialudeliveryback.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fooddelivery.tialudeliveryback.domain.Product;
import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;
import br.com.fooddelivery.tialudeliveryback.exception.ProductNotFoundException;
import br.com.fooddelivery.tialudeliveryback.mapper.ProductMapper;
import br.com.fooddelivery.tialudeliveryback.repository.ProductRepository;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEnableResponse enableProduct(String merchantId, String productId) {
        // TODO (CA-002): validar autenticação/permissão quando segurança estiver configurada

    Product product = productRepository.findByMerchantIdAndId(merchantId, productId)
        .orElseThrow(() -> new ProductNotFoundException(productId, merchantId));

        boolean changed = false;
        if (!product.isDisponivel()) {
            product.setDisponivel(true);
            productRepository.save(product);
            changed = true;
        }

        // Use mapper overload to include whether the state changed during this operation
        return ProductMapper.toEnableResponse(product, product.isDisponivel(), changed);
    }
}
