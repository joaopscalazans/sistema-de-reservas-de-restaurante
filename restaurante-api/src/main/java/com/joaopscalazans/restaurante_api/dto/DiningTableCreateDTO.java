package com.joaopscalazans.restaurante_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DiningTableCreateDTO(
    @NotBlank(message = "Não pode ser vaziu")
    @Size(min = 1, max = 50, message = "O nome deve ter entre 1 e 50 caracteres")
    String name,
    @NotNull(message = "Não pode ser nulo")
    Integer capacity) {

}
