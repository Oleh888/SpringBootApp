package spring.boot.app.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.boot.app.demo.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(nativeQuery = true, value = "SELECT profile_name FROM user "
            + "GROUP BY profile_name "
            + "ORDER BY COUNT(profile_name) desc "
            + "LIMIT :limit ")
    List<String> getMostActiveLimitedTo(@Param("limit") int limit);
}
