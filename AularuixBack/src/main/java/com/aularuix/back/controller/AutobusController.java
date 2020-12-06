package com.aularuix.back.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.aularuix.back.dto.AutobusDto;
import com.aularuix.back.dto.ComedorDto;
import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.entity.Autobus;
import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Dieta;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.service.UsuarioService;
import com.aularuix.back.service.AutobusService;
import com.aularuix.back.service.DietaService;

@RestController
@RequestMapping("/autobus")
@CrossOrigin(origins = "*")
public class AutobusController {

	@Autowired
    AutobusService autobusService;

	@Autowired
    UsuarioService  usuarioService;
	
	@GetMapping("/autobuses")
	public ResponseEntity<List<Autobus>> list() {
		List<Autobus> list = autobusService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AutobusDto autobusDto){
      Autobus autobus = new Autobus();
        autobusService.save(autobus);
        return new ResponseEntity(new Mensaje("autobus creado"), HttpStatus.OK);
    }
	 
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody AutobusDto autobusDto){
        
		Autobus autobus = autobusService.getOne(id).get();
        autobus.setUsuarios(autobusDto.getUsuarios());
        
        autobusService.save(autobus);
        return new ResponseEntity(new Mensaje("Comedor actualizado"), HttpStatus.OK);
    }

	@GetMapping("/inscribe/{nombreUsuario}")
    public ResponseEntity<?> inscribe(@PathVariable("nombreUsuario")String nombreUsuario){
        Mensaje response = new Mensaje(null);
        
        Autobus autobus = autobusService.getOne(1).get();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).get();
		List<Usuario> usuarios = autobus.getUsuarios();
		
        if (!usuarios.contains(usuario)) {
        	
        	usuario.setInAutobus(true);
        	usuarios.add(usuario);
        	autobus.setUsuarios(usuarios);
        	
        	usuarioService.save(usuario);
        	autobusService.save(autobus);
        	
        	response = new Mensaje("Usuario registrado");
        
        }else {
        	
//        	No utilizo forEach porque Spring otorga a las 
//        	 List un tamaño en cache de -1 y da nullPointer
        	for (int i = 0; i < usuarios.size(); i++) {
        		if (usuarios.get(i).getNombre().equals(usuario.getNombre())) {
					usuarios.remove(i);
        		}
			}
        	usuario.setInAutobus(false);
        	autobus.setUsuarios(usuarios);
        	
        	usuarioService.save(usuario);
        	autobusService.save(autobus);
        	
        	response = new Mensaje("Usuario Eliminado");
        }
       
     return new ResponseEntity(response, HttpStatus.OK);

	}
	
	@GetMapping("/desInscribe")
    public ResponseEntity<?> desInscribe() {
        Mensaje response = new Mensaje(null);
        
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).get();
        List<Autobus> autobus = autobusService.list();
        List<Usuario> users = new ArrayList<Usuario>();
        for (int i = 0; i < autobus.size(); i++) {
			users = autobus.get(i).getUsuarios();
				for (int y = 0; y < users.size(); y++) {
	        		if (users.get(y).getNombre().equals(usuario.getNombre())) {
						users.remove(y);
						usuario.setInAutobus(false);
						usuarioService.save(usuario);
	        		}
				}
				
				autobus.get(i).setUsuarios(users);
				
	        	autobusService.save(autobus.get(i));
			}
        	
        	response = new Mensaje("Usuario eliminado");
        
     return new ResponseEntity(response, HttpStatus.OK);

	}

}
