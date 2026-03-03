package com.joaopscalazans.restaurante_api.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTable;
import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTableStatus;
import com.joaopscalazans.restaurante_api.domain.reserve.Reserve;
import com.joaopscalazans.restaurante_api.domain.reserve.ReserveStatus;
import com.joaopscalazans.restaurante_api.domain.user.User;
import com.joaopscalazans.restaurante_api.dto.DiningTableResponseDTO;
import com.joaopscalazans.restaurante_api.dto.ReserveRequestDTO;
import com.joaopscalazans.restaurante_api.dto.ReserveResponseDTO;
import com.joaopscalazans.restaurante_api.dto.UserResponseDTO;
import com.joaopscalazans.restaurante_api.repository.DiningTableRespository;
import com.joaopscalazans.restaurante_api.repository.ReserveRepository;
import com.joaopscalazans.restaurante_api.repository.UserRepository;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private DiningTableRespository diningTableRespository;
    @Autowired
    private UserRepository userRepository;

    public void save(ReserveRequestDTO entity,UserDetails userDetails){

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(); 
        DiningTable diningTable = diningTableRespository.findById(entity.table()).orElseThrow();

        //Valida o Status se a mesa está ativa
        if(diningTable.getStatus().equals(DiningTableStatus.INACTIVE)) throw new RuntimeException();

        //Valida que o data com pelos menos 1h de antecedência
        if(entity.date().isBefore(LocalDateTime.now().plus(1, ChronoUnit.HOURS))) throw new RuntimeException();

        //Valida se existe uma reserva para mesa N no mesmo intervalo de tempo
        var starMinusHours = entity.date().minusHours(2);
        var end = entity.date().plusHours(2);
        
        List<Reserve> reserves = reserveRepository.findAllConflicts(diningTable.getId(), starMinusHours, end);
        if(!reserves.isEmpty()) 
            throw new RuntimeException();

        reserveRepository.save(new Reserve(
            entity.date(),
            user,
            diningTable,
            ReserveStatus.ACTIVED
        ));
    }

    public List<ReserveResponseDTO> findByUser(UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return reserveRepository.findByUser_Id(user.getId())
        .stream()
        .map(res -> new ReserveResponseDTO(
            res.getId(),
            res.getDate(),
            new UserResponseDTO(
                res.getUser().getName(),
                res.getUser().getEmail(),
                res.getUser().getRole()
            ),
            new DiningTableResponseDTO(
                res.getDiningTable().getName(),
                res.getDiningTable().getCapacity(),
                res.getDiningTable().getStatus()
            ))).toList();
    }

    public void cancel(Long id){
        Reserve reserve = reserveRepository.findById(id).orElseThrow();
        reserve.setStatus(ReserveStatus.CANCELLED);
        reserveRepository.save(reserve);
    }

}
