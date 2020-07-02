package spring.boot.app.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import spring.boot.app.demo.model.Product;
import spring.boot.app.demo.repository.ProductRepository;
import spring.boot.app.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
