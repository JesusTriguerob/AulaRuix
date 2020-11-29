package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.repository.ZonaComunRepository;

@Service
@Transactional
public class ZonaComunService {
	
	@Autowired
	ZonaComunRepository zonaComunRepository;
	
	public List<ZonaComun> list(){
        return zonaComunRepository.findAll();
    }

    public Optional<ZonaComun> getOne(int id){
        return zonaComunRepository.findById(id);
    }

    public void  save(ZonaComun aula){
    	zonaComunRepository.save(aula);
    }

    public void delete(int id){
    	zonaComunRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return zonaComunRepository.existsById(id);
    }

}
