package spring.boot.app.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import spring.boot.app.demo.repository.ProductRepository;
import spring.boot.app.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<String> getMostCommentedLimitedTo(int limit) {
        return productRepository.getMostCommentedLimitedTo(limit);
    }
}
