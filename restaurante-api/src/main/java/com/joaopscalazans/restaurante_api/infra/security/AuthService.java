package com.joaopscalazans.restaurante_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.joaopscalazans.restaurante_api.dto.LoginDTO;
import com.joaopscalazans.restaurante_api.infra.exceptions.UnauthorizedException;

@Service
public class AuthService {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private TokenService tokenService;

  public String login(LoginDTO entity) {
    try {
      var usernamePassword = new UsernamePasswordAuthenticationToken(entity.email(), entity.password());
      var authenticated = authenticationManager.authenticate(usernamePassword);
      String token = tokenService.generateToken(authenticated.getName());
      return token;
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException("Usuário ou senha incorretos");
    }

  }

}
