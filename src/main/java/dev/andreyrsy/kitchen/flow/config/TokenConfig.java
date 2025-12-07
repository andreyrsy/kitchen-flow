package dev.andreyrsy.kitchen.flow.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.andreyrsy.kitchen.flow.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenConfig {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            try {
                String token = JWT.create()
                        .withIssuer("auth-api")
                        .withSubject(user.getLogin())
                        .withExpiresAt(generateExpiationDate())
                        .sign(algorithm);
                return token;
            } catch (JWTCreationException exception) {
                throw new RuntimeException("Error while generating JWT token", exception);
            }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            return "";
        }
    }

    private Instant generateExpiationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
