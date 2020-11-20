package com.aularuix.back.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aularuix.back.dto.LibroDto;
import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.entity.Libro;
import com.aularuix.back.enums.Alquilado;
import com.aularuix.back.enums.Estado;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.entity.UsuarioPrincipal;
import com.aularuix.back.security.jwt.JwtProvider;
import com.aularuix.back.security.service.UsuarioService;
import com.aularuix.back.service.LibroService;

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    LibroService libroService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/lista")
    public ResponseEntity<List<Libro>> list(){
        List<Libro> list = libroService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Libro> getById(@PathVariable("id") int id){
        if(!libroService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Libro libro = libroService.getOne(id).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Libro> getByNombre(@PathVariable("nombre") String nombre){
        if(!libroService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Libro libro = libroService.getByNombre(nombre).get();
        return new ResponseEntity(libro, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody LibroDto libroDto){
        if(StringUtils.isBlank(libroDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(libroService.existsByNombre(libroDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Libro libro = new Libro(libroDto.getNombre(), libroDto.getCategoria(), Estado.NUEVO, Alquilado.DISPONIBLE, libroDto.getUsuario(),null,null);
        libroService.save(libro);
        return new ResponseEntity(new Mensaje("libro creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody LibroDto libroDto){
        if(!libroService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(libroService.existsByNombre(libroDto.getNombre()) && libroService.getByNombre(libroDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(libroDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       
        Libro libro = libroService.getOne(id).get();
        libro.setNombre(libroDto.getNombre());
        libro.setCategoria(libroDto.getCategoria());
        
        libroService.save(libro);
        return new ResponseEntity(new Mensaje("libro actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!libroService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        libroService.delete(id);
        return new ResponseEntity(new Mensaje("libro eliminado"), HttpStatus.OK);
    }
    
    @GetMapping("/alquilar/{id}")
    public ResponseEntity<?> alquilar(@PathVariable("id")int id){
        if(!libroService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        
        Mensaje response = new Mensaje(null);
        
        Libro libro = libroService.getOne(id).get();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).get();
		
		List<Libro> libros = usuario.getLibros();
		
        if (libro.getAlquilado() == Alquilado.ALQUILADO) {
        	
        	libro.setAlquilado(Alquilado.DISPONIBLE);
        	libro.setUsuario("");
        	libro.setFechaAlquiler(null);
        	libro.setFechaDevolucion(null);
        	
        	//No utilizo forEach porque Spring otorga a las 
        	// List un tamaño en cache de -1 y da nullPointer
        	for (int i = 0; i < libros.size(); i++) {
        		if (libros.get(i).getNombre().equals(libro.getNombre())) {
					libros.remove(i);
        		}
			}

        	libro.setEstado(libro.getVecesAlquilado() >= 10 ? Estado.NO_APTO :
        		libro.getVecesAlquilado() >= 6 ? Estado.USADO : 
        			libro.getVecesAlquilado() >= 3 ? Estado.SEMINUEVO :
        			Estado.NUEVO);
        	
        	if (libro.getVecesAlquilado() >= 10) {
        		libroService.delete(libro.getId());
        		return new ResponseEntity(new Mensaje("Se ha sobrepasado el numero "
        				+ "maximo de alquileres, Se elimina el libro."
        				+ "refresca la página."), HttpStatus.NOT_ACCEPTABLE);
			}
        	usuario.setLibros(libros);
        	
        	response = new Mensaje("Libro devuelto");
        			
		} else if (libro.getAlquilado() == Alquilado.DISPONIBLE){
			
			libro.setAlquilado(Alquilado.ALQUILADO);
			LocalDate date = LocalDate.now();
			libro.setFechaAlquiler(date.now());
			libro.setFechaDevolucion(libro.getFechaAlquiler().plusDays(30));
			libro.setUsuario(usuario.getNombreUsuario());
			int vecesAlquilado = libro.getVecesAlquilado();
        	libro.setVecesAlquilado(vecesAlquilado+1);

			libros.add(libro);
			
			usuario.setLibros(libros);
			
			response = new Mensaje("Libro alquilado");
		}
        
        libroService.save(libro);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
