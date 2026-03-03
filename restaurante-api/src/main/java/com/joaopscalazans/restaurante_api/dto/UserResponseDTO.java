package com.joaopscalazans.restaurante_api.dto;

import com.joaopscalazans.restaurante_api.domain.user.UserRole;

public record UserResponseDTO(String name,String email,UserRole role) {

}
