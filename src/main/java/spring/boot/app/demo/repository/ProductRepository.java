package spring.boot.app.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.boot.app.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT user.product.id FROM User user "
            + "GROUP BY user.product.id "
            + "ORDER BY count(user.product.id) desc 
            + "LIMIT :limit ")
    List<String> getMostCommentedLimitedTo(@Param("limit") int limit);
}
