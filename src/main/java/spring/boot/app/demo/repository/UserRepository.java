package spring.boot.app.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.boot.app.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT profile_name FROM user "
            + "GROUP BY profile_name "
            + "ORDER BY COUNT(profile_name) desc "
            + "LIMIT :limit ")
    List<String> getMostActiveLimitedTo(@Param("limit") int limit);

    @Query("SELECT user FROM User user "
            + "WHERE user.nativeId = :nativeId ")
    Optional<User> findByNativeId(@Param("nativeId") String nativeId);
}
