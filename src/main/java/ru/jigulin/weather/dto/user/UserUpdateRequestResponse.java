package ru.jigulin.weather.dto.user;

import ru.jigulin.weather.entity.User;

public class UserUpdateRequestResponse {
    private String username;
    private Boolean emptySubs;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEmptySubs() {
        return emptySubs;
    }

    public void setEmptySubs(Boolean emptySubs) {
        this.emptySubs = emptySubs;
    }

    public static UserUpdateRequestResponse from(User user) {
        var response = new UserUpdateRequestResponse();

        if (user != null) {
            response.setUsername(user.getUsername());
            response.setEmptySubs(user.getSubscriptions().isEmpty());
        }

        return response;
    }

}
