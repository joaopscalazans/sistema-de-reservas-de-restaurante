package com.joaopscalazans.restaurante_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaopscalazans.restaurante_api.domain.reserve.Reserve;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {

}
