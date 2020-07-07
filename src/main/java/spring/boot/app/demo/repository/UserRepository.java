package spring.boot.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.app.demo.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
