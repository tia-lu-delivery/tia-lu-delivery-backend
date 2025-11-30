package br.com.fooddelivery.tialudeliveryback.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fooddelivery.tialudeliveryback.domain.Product;
import br.com.fooddelivery.tialudeliveryback.dto.ProductEnableResponse;
import br.com.fooddelivery.tialudeliveryback.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.fooddelivery.tialudeliveryback.mapper.ProductMapper;
import br.com.fooddelivery.tialudeliveryback.repository.ProductRepository;
import br.com.fooddelivery.tialudeliveryback.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEnableResponse enableProduct(String merchantId, String productId) {
        // Validação provisória de autorização é feita por um filtro (XMerchantIdFilter).
        log.debug("Habilitando produto {} para estabelecimento {}", productId, merchantId);

    Product product = productRepository.findByMerchantIdAndId(merchantId, productId)
        .orElseThrow(() -> new ProductNotFoundException(productId, merchantId));

        boolean changed = false;
        if (!product.isDisponivel()) {
            product.setDisponivel(true);
            productRepository.save(product);
            changed = true;
            log.info("Produto {} reativado para merchant {}", productId, merchantId);
        }

        // Use mapper overload to include whether the state changed during this operation
        ProductEnableResponse resp = ProductMapper.toEnableResponse(product, product.isDisponivel(), changed);
        log.debug("Resposta enableProduct: {}", resp);
        return resp;
    }
}
