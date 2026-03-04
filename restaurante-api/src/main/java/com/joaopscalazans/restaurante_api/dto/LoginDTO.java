package com.joaopscalazans.restaurante_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(
        @NotNull(message = "Não pode ser nulo") @Email(message = "Formato do email inválido") String email,
        @NotNull(message = "Não pode ser nulo") String password) {

}
