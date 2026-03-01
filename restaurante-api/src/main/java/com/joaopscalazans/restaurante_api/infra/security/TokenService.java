package com.joaopscalazans.restaurante_api.infra.security;

import java.time.Instant;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${api.security.jwt.secret}")
    private String secret;
    private final String ISSUER = "restaurante-api";

    public String generateToken(String subject){

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
        .withIssuer(ISSUER)
        .withExpiresAt(timeExpiresAt())
        .withSubject(subject)
        .sign(algorithm);
    }

    private Instant timeExpiresAt(){
        return Instant.now().plus(4, ChronoUnit.HOURS);
    }

    public String validToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();
    }


}
