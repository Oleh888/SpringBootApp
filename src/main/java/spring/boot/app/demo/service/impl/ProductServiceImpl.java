package spring.boot.app.demo.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import spring.boot.app.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private final EntityManager entityManager;

    public ProductServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> getMostCommentedLimitedTo(int limit) {
        return entityManager.createQuery("SELECT user.product.id FROM User user "
                + "GROUP BY user.product.id "
                + "ORDER BY count(user.product.id) desc ", String.class)
                .setMaxResults(limit).getResultList();
    }
}
