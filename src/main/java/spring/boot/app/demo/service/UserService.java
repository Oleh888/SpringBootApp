package spring.boot.app.demo.service;

import java.util.List;
import java.util.Optional;
import spring.boot.app.demo.model.User;

public interface UserService {
    User create(User user);

    List<User> findAll();

    void delete(User user);

    List<String> getMostActiveLimitedTo(int limit);

    Optional<User> findByNativeId(String nativeId);

    List<User> createAll(List<User> users);
}
