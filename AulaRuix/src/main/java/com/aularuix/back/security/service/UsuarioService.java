package com.aularuix.back.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> list(){
    	return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> getOne(int id){
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> getByNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }
    
    public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }
    
    public void delete(int id){
    	usuarioRepository.deleteById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return usuarioRepository.existsByNombre(nombre);
    }

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
