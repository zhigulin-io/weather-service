package ru.jigulin.weather.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtils {

    private final String secret = "TOO_SECRET";
    private final String issuer = "weather-service";
    private final String subject = "auth-token";

    public String generateToken(String username) {
        var now = new Date();
        var expDate = Date.from(now.toInstant().plusSeconds(3600));
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(expDate)
                .withClaim("username", username)
                .withIssuer(issuer)
                .withSubject(subject)
                .sign(Algorithm.HMAC256(secret));
    }

    public String verifyToken(String token) {
        String username = null;
        try {
            username = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .build()
                    .verify(token)
                    .getClaim("username")
                    .asString();
        } catch (Exception ignored) {  }

        return username;
    }


}
