package com.aularuix.back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.entity.Libro;
import com.aularuix.back.repository.LibroRepository;

@Service
@Transactional
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    public List<Libro> list(){
        return libroRepository.findAll();
    }

    public Optional<Libro> getOne(int id){
        return libroRepository.findById(id);
    }

    public Optional<Libro> getByNombre(String nombre){
        return libroRepository.findByNombre(nombre);
    }

    public void  save(Libro producto){
    	libroRepository.save(producto);
    }

    public void delete(int id){
    	libroRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return libroRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return libroRepository.existsByNombre(nombre);
    }
}
