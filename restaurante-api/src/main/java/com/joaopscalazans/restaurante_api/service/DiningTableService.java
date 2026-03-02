package com.joaopscalazans.restaurante_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTable;
import com.joaopscalazans.restaurante_api.domain.diningtable.DiningTableStatus;
import com.joaopscalazans.restaurante_api.dto.DiningTableCreateDTO;
import com.joaopscalazans.restaurante_api.dto.DiningTableRequestDTO;
import com.joaopscalazans.restaurante_api.infra.utils.ObjectUtils;
import com.joaopscalazans.restaurante_api.repository.DiningTableRespository;

@Service
public class DiningTableService {

    @Autowired
    private DiningTableRespository tableRespository;

    public void save(DiningTableCreateDTO entity){
        tableRespository.save(new DiningTable(
            entity.name(),
            entity.capacity(),
            DiningTableStatus.AVAILABLE
        ));
    }
    public List<DiningTable> findAll(){
        return tableRespository.findAll();
    }
    public void update(Long id,DiningTableRequestDTO entity){
        System.out.println(entity);
        DiningTable tableTarget = tableRespository.findById(id).orElseThrow();
        ObjectUtils.copyNonNullProperties(entity, tableTarget);
        tableRespository.save(tableTarget);
    }

    public void delete(Long id){
        DiningTable table = tableRespository.findById(id).orElseThrow();
        tableRespository.delete(table);
    }

}
