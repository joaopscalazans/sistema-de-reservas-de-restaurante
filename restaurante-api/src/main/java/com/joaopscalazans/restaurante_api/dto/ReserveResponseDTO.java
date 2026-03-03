package com.joaopscalazans.restaurante_api.dto;

import java.time.LocalDateTime;

public record ReserveResponseDTO(Long id,LocalDateTime date,UserResponseDTO user,DiningTableResponseDTO table) {

}
