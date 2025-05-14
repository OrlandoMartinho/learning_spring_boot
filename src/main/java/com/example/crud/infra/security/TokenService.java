package com.example.crud.infra.security;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.crud.domain.user.User;
@Service

public class TokenService {
    @Value("${api.security.token.secret}")
    private  String secret;

    public String generateToken(User user) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           var token = com.auth0.jwt.JWT.create()
                   .withIssuer("auth-api")
                   .withSubject(user.getLogin())
                   .withExpiresAt(genExpirationDate())
                   .sign(algorithm);
           return token;

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public boolean isTokenValid(String token) {
        // Implement your token validation logic here
        // For example, you can decode the token and check its expiration date
        return token.startsWith("generated-token-for-");
    }

    public String getLoginFromToken(String token) {
        // Implement your logic to extract the login from the token
        return token.substring("generated-token-for-".length());
    }

    private Instant genExpirationDate() {
        // Implement your logic to extract the expiration date from the token
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
