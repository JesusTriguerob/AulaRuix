package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.Aula;
import com.aularuix.back.repository.AulaRepository;

@Service
@Transactional
public class AulaService {
	
	@Autowired
	AulaRepository aulaRepository;
	
	public List<Aula> list(){
        return aulaRepository.findAll();
    }

    public Optional<Aula> getOne(int id){
        return aulaRepository.findById(id);
    }

    public void  save(Aula aula){
    	aulaRepository.save(aula);
    }

    public void delete(int id){
    	aulaRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return aulaRepository.existsById(id);
    }

}
