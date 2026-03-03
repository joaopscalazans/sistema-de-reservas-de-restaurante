package com.joaopscalazans.restaurante_api.dto;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTableStatus;

public record DiningTableResponseDTO(String name,Integer capacity,DiningTableStatus status) {

}
