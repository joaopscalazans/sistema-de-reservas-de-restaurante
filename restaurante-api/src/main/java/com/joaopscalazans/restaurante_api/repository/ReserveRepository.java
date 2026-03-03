package com.joaopscalazans.restaurante_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joaopscalazans.restaurante_api.domain.reserve.Reserve;


public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    List<Reserve> findByUser_Id(UUID user);

    @Query("""
        SELECT r FROM Reserve r 
        WHERE r.diningTable.id = :tableId
        AND r.status = 'ACTIVED'
        AND r.date BETWEEN :startMinusHours AND :end
        """)
    List<Reserve> findAllConflicts(Long tableId,LocalDateTime startMinusHours, LocalDateTime end);

}
