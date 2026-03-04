package com.joaopscalazans.restaurante_api.dto;

import java.time.LocalDateTime;

public record ExceptionDTO(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path
) {

}
