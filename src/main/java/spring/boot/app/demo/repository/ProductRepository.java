package spring.boot.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.app.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
