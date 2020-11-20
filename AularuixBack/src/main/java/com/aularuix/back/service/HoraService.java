package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.Aula;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.repository.AulaRepository;
import com.aularuix.back.repository.HoraRepository;

@Service
@Transactional
public class HoraService {
	
	@Autowired
	HoraRepository horaRepository;
	
	public List<Hora> list(){
        return horaRepository.findAll();
    }

    public Optional<Hora> getOne(int id){
        return horaRepository.findById(id);
    }

    public void  save(Hora hora){
    	horaRepository.save(hora);
    }

    public void delete(int id){
    	horaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return horaRepository.existsById(id);
    }

}
