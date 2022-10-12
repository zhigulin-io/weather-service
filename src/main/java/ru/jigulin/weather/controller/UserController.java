package ru.jigulin.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jigulin.weather.dto.user.UserResponse;
import ru.jigulin.weather.dto.user.UserUpdateRequestResponse;
import ru.jigulin.weather.dto.user.UserWithSubsResponse;
import ru.jigulin.weather.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDetailsServiceImpl userService;

    @Autowired
    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserResponse> readAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserWithSubsResponse readUser(@PathVariable Long id) {
        return userService.getUserWithSubscriptions(id);
    }

    @PutMapping("/{id}")
    public UserUpdateRequestResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestResponse req) {
        return userService.updateUser(id, req);
    }
}
