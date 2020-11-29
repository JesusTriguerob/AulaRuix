package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.repository.ZonaComunRepository;
import com.aularuix.back.repository.ComedorRepository;

@Service
@Transactional
public class ComedorService {
	
	@Autowired
	ComedorRepository comedorRepository;
	
	public List<Comedor> list(){
        return comedorRepository.findAll();
    }

    public Optional<Comedor> getOne(int id){
        return comedorRepository.findById(id);
    }

    public void  save(Comedor comedor){
    	comedorRepository.save(comedor);
    }

}
