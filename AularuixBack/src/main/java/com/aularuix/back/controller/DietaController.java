package com.aularuix.back.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aularuix.back.dto.ZonaComunDto;
import com.aularuix.back.dto.DietaDto;
import com.aularuix.back.dto.LibroDto;
import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.dto.UsuarioDto;
import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Dieta;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.entity.Libro;
import com.aularuix.back.enums.Alquilado;
import com.aularuix.back.enums.Estado;
import com.aularuix.back.enums.Reservado;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.service.UsuarioService;
import com.aularuix.back.service.ZonaComunService;
import com.aularuix.back.service.DietaService;
import com.aularuix.back.service.HoraService;

@RestController
@RequestMapping("/dieta")
@CrossOrigin(origins = "*")
public class DietaController {

	@Autowired
	DietaService dietaService;
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/dietas")
	public ResponseEntity<List<Dieta>> list() {
		List<Dieta> list = dietaService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DietaDto dietaDto){
        if(StringUtils.isBlank(dietaDto.getNombreDieta()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        Dieta dieta = new Dieta(dietaDto.getNombreDieta(), new ArrayList<Usuario>());
        dietaService.save(dieta);
        return new ResponseEntity(new Mensaje("dieta creado"), HttpStatus.OK);
    }
	 
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DietaDto dietaDto){
        
        Dieta dieta = dietaService.getOne(id).get();
        dieta.setNombreDieta(dietaDto.getNombreDieta());
        dieta.setUsuarios(dietaDto.getUsuarios());
        
        dietaService.save(dieta);
        return new ResponseEntity(new Mensaje("Dieta actualizada"), HttpStatus.OK);
    }

	@GetMapping("/inscribe/{id}")
    public ResponseEntity<?> inscribe(@PathVariable("id")int id){
        Mensaje response = new Mensaje(null);
        
        Dieta dieta = dietaService.getOne(id).get();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).get();
		List<Usuario> usuarios = dieta.getUsuarios();
		
        if (!usuarios.contains(usuario)) {
        	
        	usuario.setInComedor(true);
        	usuarios.add(usuario);
        	dieta.setUsuarios(usuarios);
        	
        	usuarioService.save(usuario);
        	dietaService.save(dieta);
        	
        	response = new Mensaje("Usuario registrado");
        
        }else {
        	
//        	No utilizo forEach porque Spring otorga a las 
//        	 List un tamaño en cache de -1 y da nullPointer
        	for (int i = 0; i < usuarios.size(); i++) {
        		if (usuarios.get(i).getNombre().equals(usuario.getNombre())) {
					usuarios.remove(i);
        		}
			}
        	usuario.setInComedor(false);
        	dieta.setUsuarios(usuarios);
        	
        	usuarioService.save(usuario);
        	dietaService.save(dieta);
        	
        	response = new Mensaje("Usuario Eliminado");
        }
       
     return new ResponseEntity(response, HttpStatus.OK);

	}
	
	@GetMapping("/desInscribe")
    public ResponseEntity<?> desInscribe() {
        Mensaje response = new Mensaje(null);
        
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).get();
        List<Dieta> dietas = dietaService.list();
        List<Usuario> users = new ArrayList<Usuario>();
        for (int i = 0; i < dietas.size(); i++) {
			users = dietas.get(i).getUsuarios();
				for (int y = 0; y < users.size(); y++) {
	        		if (users.get(y).getNombre().equals(usuario.getNombre())) {
						users.remove(y);
						usuario.setInComedor(false);
						usuarioService.save(usuario);
	        		}
				}
				
				dietas.get(i).setUsuarios(users);
				
	        	dietaService.save(dietas.get(i));
			}
        	
        	response = new Mensaje("Usuario eliminado");
        
     return new ResponseEntity(response, HttpStatus.OK);

	}

}
