package spring.boot.app.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.app.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
