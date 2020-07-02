package spring.boot.app.demo.service;

import java.util.List;
import java.util.Optional;
import spring.boot.app.demo.model.User;

public interface UserService {
    User save(User user);

    Optional<User> findById(String id);

    List<User> findAll();

    void delete(User user);
}
