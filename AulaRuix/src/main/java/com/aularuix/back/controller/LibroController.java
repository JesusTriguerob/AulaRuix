package com.aularuix.back.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.aularuix.back.service.LibroService;

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    LibroService libroService;

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
        Libro libro = new Libro(libroDto.getNombre(), libroDto.getCategoria(), Estado.NUEVO, Alquilado.DISPONIBLE, libroDto.getUsuario());
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


}
