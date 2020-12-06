package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Autobus;
import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.repository.ZonaComunRepository;
import com.aularuix.back.repository.AutobusRepository;
import com.aularuix.back.repository.ComedorRepository;

@Service
@Transactional
public class AutobusService {
	
	@Autowired
	AutobusRepository autobusRepository;
	
	public List<Autobus> list(){
        return autobusRepository.findAll();
    }

    public Optional<Autobus> getOne(int id){
        return autobusRepository.findById(id);
    }

    public void  save(Autobus comedor){
    	autobusRepository.save(comedor);
    }

}
