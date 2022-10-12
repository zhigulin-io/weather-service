package ru.jigulin.weather.dto.user;

import ru.jigulin.weather.entity.City;
import ru.jigulin.weather.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserWithSubsResponse {
    private Long id;
    private String username;
    private Set<String> subscriptions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getSubscriptions() {
        return subscriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubscriptions(Set<String> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public static UserWithSubsResponse from(User user) {
        var response = new UserWithSubsResponse();

        if (user != null) {
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setSubscriptions(user.getSubscriptions().stream().map(City::getName).collect(Collectors.toSet()));
        }

        return response;
    }
}
