package spring.boot.app.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.boot.app.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT native_id FROM product "
            + "GROUP BY native_id "
            + "ORDER BY COUNT(native_id) desc "
            + "LIMIT :limit ")
    List<String> getMostCommentedLimitedTo(@Param("limit") int limit);
}
