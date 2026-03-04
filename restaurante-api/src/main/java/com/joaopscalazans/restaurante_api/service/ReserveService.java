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
import com.joaopscalazans.restaurante_api.infra.exceptions.BusinessException;
import com.joaopscalazans.restaurante_api.infra.exceptions.DiningTableInactiveException;
import com.joaopscalazans.restaurante_api.infra.exceptions.EntityNotFoundException;
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

        User user = this.findByEmail(userDetails.getUsername());
        DiningTable diningTable = diningTableRespository.findById(entity.table()).orElseThrow(
            () -> new EntityNotFoundException("Mesa inexistente")
        );

        //Valida o Status se a mesa está ativa
        if(diningTable.getStatus().equals(DiningTableStatus.INACTIVE)) throw new DiningTableInactiveException("Mesa inativa");

        //Valida que o data com pelos menos 1h de antecedência
        if(entity.date().isBefore(LocalDateTime.now().plus(1, ChronoUnit.HOURS))) 
            throw new BusinessException("A reserva deve ser feita com pelo menos 1h de antecedência");

        //Valida se existe uma reserva para mesa N no mesmo intervalo de tempo
        var starMinusHours = entity.date().minusHours(2);
        var end = entity.date().plusHours(2);
        
        List<Reserve> reserves = reserveRepository.findAllConflicts(diningTable.getId(), starMinusHours, end);
        if(!reserves.isEmpty()) 
            throw new BusinessException("Já existe uma reserva para essa mesa nesse intervalo horário");

        reserveRepository.save(new Reserve(
            entity.date(),
            user,
            diningTable,
            ReserveStatus.ACTIVED
        ));
    }

    public List<ReserveResponseDTO> findByUser(UserDetails userDetails){
        User user = this.findByEmail(userDetails.getUsername());
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
        Reserve reserve = reserveRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Reserva inexistente")
        );
        reserve.setStatus(ReserveStatus.CANCELLED);
        reserveRepository.save(reserve);
    }

    private User findByEmail(String id){
        return userRepository.findByEmail(id).orElseThrow(
            () -> new EntityNotFoundException("Usuário inexistente")
        );
    }

}
