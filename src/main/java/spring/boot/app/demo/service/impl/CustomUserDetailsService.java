package spring.boot.app.demo.service.impl;

import javax.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User userFromDB = userService.findByNativeId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user with id: " + userId));
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(userId);
        builder.password(userFromDB.getPassword())
                .roles(userFromDB.getRole().toString());
        return builder.build();
    }
}
