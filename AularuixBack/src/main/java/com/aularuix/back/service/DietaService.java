package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Dieta;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.repository.ZonaComunRepository;
import com.aularuix.back.repository.ComedorRepository;
import com.aularuix.back.repository.DietaRepository;

@Service
@Transactional
public class DietaService {
	
	@Autowired
	DietaRepository dietaRepository;
	
	public List<Dieta> list(){
        return dietaRepository.findAll();
    }

    public Optional<Dieta> getOne(int id){
        return dietaRepository.findById(id);
    }

    public void save(Dieta dieta){
    	dietaRepository.save(dieta);
    }

}
