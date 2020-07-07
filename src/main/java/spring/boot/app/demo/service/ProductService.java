package spring.boot.app.demo.service;

import java.util.List;
import java.util.Optional;
import spring.boot.app.demo.model.Product;

public interface ProductService {
    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();

    void delete(Product product);
}
