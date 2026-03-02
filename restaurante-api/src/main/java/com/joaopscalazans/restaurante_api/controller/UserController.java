package com.joaopscalazans.restaurante_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.joaopscalazans.restaurante_api.dto.LoginDTO;
import com.joaopscalazans.restaurante_api.dto.RegisterDTO;
import com.joaopscalazans.restaurante_api.dto.TokenDTO;
import com.joaopscalazans.restaurante_api.infra.security.AuthService;
import com.joaopscalazans.restaurante_api.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO entity) {
         return ResponseEntity.ok(new TokenDTO(authService.login(entity)));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO entity) {
            userService.register(entity);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
} 


