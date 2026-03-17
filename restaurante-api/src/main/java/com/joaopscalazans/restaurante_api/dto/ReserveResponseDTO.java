package com.joaopscalazans.restaurante_api.dto;

import java.time.LocalDateTime;

import com.joaopscalazans.restaurante_api.domain.reserve.ReserveStatus;

public record ReserveResponseDTO(Long id,LocalDateTime date,UserResponseDTO user,DiningTableResponseDTO table,ReserveStatus status) {

}
