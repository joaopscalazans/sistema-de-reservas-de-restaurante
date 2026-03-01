package com.joaopscalazans.restaurante_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTable;

public interface DiningTableRespository extends JpaRepository<DiningTable, Long> {

}
