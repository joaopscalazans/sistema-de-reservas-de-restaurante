package com.joaopscalazans.restaurante_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;

public record RegisterDTO(
    @NotBlank(message = "Não pode ser vaziu")
    @Size(min = 1, max = 50, message = "O nome deve ter entre 1 e 50 caracteres")
    String name,
    @NotBlank(message = "Não pode ser vaziu")
    @Email(message = "Formato do email inválido")
    String email,
    @NotBlank(message = "Não pode ser vaziu")
    @Size(min = 8, max = 18, message = "A senha deve ter entre 8 e 18 caracteres")
    String password) {

}
