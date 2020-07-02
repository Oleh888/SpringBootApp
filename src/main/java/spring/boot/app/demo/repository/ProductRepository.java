package spring.boot.app.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.app.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Override
    <S extends Product> S save(S s);

    @Override
    Optional<Product> findById(String s);

    @Override
    List<Product> findAll();

    @Override
    void delete(Product product);
}
