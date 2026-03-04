package com.joaopscalazans.restaurante_api.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;


public record ReserveRequestDTO(
     @NotNull(message = "Não pode ser nulo")
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime date,
     @NotNull(message = "Não pode ser nulo")
    Long table) {

}
