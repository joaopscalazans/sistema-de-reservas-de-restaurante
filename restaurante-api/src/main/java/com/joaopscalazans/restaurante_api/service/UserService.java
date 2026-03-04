package com.joaopscalazans.restaurante_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joaopscalazans.restaurante_api.domain.user.User;
import com.joaopscalazans.restaurante_api.domain.user.UserRole;
import com.joaopscalazans.restaurante_api.dto.RegisterDTO;
import com.joaopscalazans.restaurante_api.infra.exceptions.BusinessException;
import com.joaopscalazans.restaurante_api.repository.UserRepository;

@Service 
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

  

    public void register(RegisterDTO entity) {
        if (userRepository.findByEmail(entity.email()).isPresent())
            throw new BusinessException("Email já cadastrado!");
    
        userRepository.save(new User(
            entity.name(),
            entity.email(),
            passwordEncoder.encode(entity.password()),
            UserRole.CLIENT
        )); 
    }

}
