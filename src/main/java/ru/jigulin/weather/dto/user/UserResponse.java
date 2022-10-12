package ru.jigulin.weather.dto.user;

import ru.jigulin.weather.entity.User;

public class UserResponse {
    private Long id;
    private String username;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static UserResponse from(User user) {
        var response = new UserResponse();

        if (user != null) {
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setRole(user.getRole().getName());
        }

        return response;
    }
}
