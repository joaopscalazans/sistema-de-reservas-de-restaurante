package com.joaopscalazans.restaurante_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTable;
import com.joaopscalazans.restaurante_api.dto.DiningTableCreateDTO;
import com.joaopscalazans.restaurante_api.dto.DiningTableRequestDTO;
import com.joaopscalazans.restaurante_api.service.DiningTableService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/dining-table")
public class DiningTableController {

    @Autowired
    private DiningTableService diningTableService;

    @GetMapping
    public ResponseEntity<List<DiningTable>> findAll() {
        return ResponseEntity.ok(this.diningTableService.findAll());
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody DiningTableCreateDTO entity) {
       this.diningTableService.save(entity);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody DiningTableRequestDTO entity){
        this.diningTableService.update(id, entity);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.diningTableService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

}
