package ru.jigulin.weather.dto.auth;

public class TokenResponse {
    private String jwt;

    public TokenResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
