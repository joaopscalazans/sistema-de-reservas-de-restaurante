package com.joaopscalazans.restaurante_api.dto;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTableStatus;

public record DiningTableRequestDTO(
    String name,
    Integer capacity,
    DiningTableStatus status) {

}
