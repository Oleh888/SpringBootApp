package spring.boot.app.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import spring.boot.app.demo.model.dto.UserRequestDto;
import spring.boot.app.demo.model.dto.UserResponseDto;
import spring.boot.app.demo.util.JwtUtil;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto createAuthenticationToken(@RequestBody UserRequestDto userRequestDto){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getNativeId(), userRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is not right ", e);
        }
        return new UserResponseDto(jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal()));
    }
}
