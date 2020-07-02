package spring.boot.app.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.repository.ProductRepository;
import spring.boot.app.demo.repository.UserRepository;
import spring.boot.app.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User save(User user) {
        productRepository.save(user.getProduct());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
