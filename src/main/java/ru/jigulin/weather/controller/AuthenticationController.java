package ru.jigulin.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.jigulin.weather.dto.auth.AuthRequest;
import ru.jigulin.weather.dto.auth.TokenResponse;
import ru.jigulin.weather.entity.User;
import ru.jigulin.weather.service.RoleService;
import ru.jigulin.weather.service.UserDetailsServiceImpl;
import ru.jigulin.weather.util.JWTUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;
    private final RoleService roleService;

    @Autowired
    public AuthenticationController(
            UserDetailsServiceImpl userDetailsService,
            PasswordEncoder passwordEncoder,
            JWTUtils jwtUtils,
            AuthenticationProvider authenticationProvider, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationProvider = authenticationProvider;
        this.roleService = roleService;
    }

    @PostMapping("/reg")
    public TokenResponse createUser(@RequestBody AuthRequest regData) {
        validateRequest(regData);

        var user = new User();
        user.setUsername(regData.getUsername());
        user.setPassword(passwordEncoder.encode(regData.getPassword()));
        user.setRole(roleService.getDefaultRole());

        userDetailsService.createNewUser(user);

        return new TokenResponse(jwtUtils.generateToken(regData.getUsername()));
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthRequest authData) {
        validateRequest(authData);
        var authToken = new UsernamePasswordAuthenticationToken(authData.getUsername(), authData.getPassword());
        authenticationProvider.authenticate(authToken);
        return new TokenResponse(jwtUtils.generateToken(authData.getUsername()));
    }

    private void validateRequest(AuthRequest req) {
        if (req.getUsername() == null) {
            throw new RuntimeException("Username is null");
        }
        if (req.getPassword() == null) {
            throw new RuntimeException("Password is null");
        }
    }

    @ExceptionHandler
    private ResponseEntity<String> handleAuthenticationError(Exception ex) {
        return new ResponseEntity<>("Authentication Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
