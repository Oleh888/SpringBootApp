package spring.boot.app.demo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.model.dto.UserRequestDto;
import spring.boot.app.demo.repository.UserRepository;
import spring.boot.app.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRole(userRequestDto.getRole());
        user.setProfileName(userRequestDto.getName());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<String> getMostActiveLimitedTo(int limit) {
        return userRepository.getMostActiveLimitedTo(limit);
    }

    @Override
    public Optional<User> findByNativeId(String nativeId) {
        return userRepository.findByNativeId(nativeId);
    }

    @Override
    public List<User> createAll(List<User> users) {
        return userRepository.saveAll(users);
    }
}
