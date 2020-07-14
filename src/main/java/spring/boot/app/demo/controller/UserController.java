package spring.boot.app.demo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.app.demo.model.User;
import spring.boot.app.demo.model.dto.UserRequestDto;
import spring.boot.app.demo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/most-active")
    public List<String> getMostActiveUsers(@RequestParam int limit) {
        return userService.getMostActiveLimitedTo(limit);
    }

    @PostMapping(value = "/")
    public User register(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.create(userRequestDto);
    }
}
