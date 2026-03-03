package com.joaopscalazans.restaurante_api.dto;

import java.time.LocalDateTime;


public record ReserveRequestDTO(LocalDateTime date,Long table) {

}
