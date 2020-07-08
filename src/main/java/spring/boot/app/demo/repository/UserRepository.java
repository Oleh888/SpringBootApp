package spring.boot.app.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.boot.app.demo.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT user.profileName FROM User user "
            + "GROUP BY user.profileName "
            + "ORDER BY count(user.profileName) desc ")
    List<String> getMostActiveLimitedTo(int limit);
}
