package br.com.fooddelivery.tialudeliveryback.service;

import br.com.fooddelivery.tialudeliveryback.entity.Product;
import br.com.fooddelivery.tialudeliveryback.repository.ProductRepository;
import br.com.fooddelivery.tialudeliveryback.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product updatedProduct) {
        Product existing = findById(id);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setDescription(updatedProduct.getDescription());
        existing.setActive(updatedProduct.getActive());
        return repository.save(existing);
    }

    public void delete(Long id) {
        Product product = findById(id);
        repository.delete(product);
    }
}
