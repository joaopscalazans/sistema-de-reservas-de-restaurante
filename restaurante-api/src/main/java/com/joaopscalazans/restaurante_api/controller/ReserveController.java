package com.joaopscalazans.restaurante_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.joaopscalazans.restaurante_api.dto.ReserveRequestDTO;
import com.joaopscalazans.restaurante_api.dto.ReserveResponseDTO;
import com.joaopscalazans.restaurante_api.service.ReserveService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid ReserveRequestDTO entity,@AuthenticationPrincipal UserDetails user) {
        reserveService.save(entity,user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ReserveResponseDTO>> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(reserveService.findByUser(userDetails));
    }
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id){
        reserveService.cancel(id);
        return ResponseEntity.noContent().build();
    }
    
    

}
