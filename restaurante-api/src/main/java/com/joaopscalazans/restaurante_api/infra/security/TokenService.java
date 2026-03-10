package com.joaopscalazans.restaurante_api.infra.security;

import java.time.Instant;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.joaopscalazans.restaurante_api.domain.user.User;
import com.joaopscalazans.restaurante_api.infra.exceptions.UnauthorizedException;
import com.joaopscalazans.restaurante_api.repository.UserRepository;


@Service
public class TokenService {

    @Value("${api.security.jwt.secret}")
    private String secret;
    private final String ISSUER = "restaurante-api";
    @Autowired
    private UserRepository userRepository;

    public String generateToken(String subject) {

        User user = userRepository.findByEmail(subject).get();
        var role = user.getRole();

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
        .withIssuer(ISSUER)
        .withExpiresAt(timeExpiresAt())
        .withClaim("role", role.name())
        .withSubject(subject)
        .sign(algorithm);
    }

    private Instant timeExpiresAt(){
        return Instant.now().plus(4, ChronoUnit.HOURS);
    }

    public String validToken(String token){
       try {
         Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();
       } catch (Exception e) {
        throw new UnauthorizedException("Token inválido ou expirado");
       }
    }


}
