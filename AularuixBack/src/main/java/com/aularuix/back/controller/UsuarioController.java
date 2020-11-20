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

import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.dto.UsuarioDto;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/lista")
	public ResponseEntity<List<Usuario>> list() {
		List<Usuario> list = usuarioService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") int id) {
		if (!usuarioService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Usuario Usuario = usuarioService.getOne(id).get();
		return new ResponseEntity(Usuario, HttpStatus.OK);
	}

	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Usuario> getByNombre(@PathVariable("nombre") String nombre) {
		if (!usuarioService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Usuario Usuario = usuarioService.getByNombre(nombre).get();
		return new ResponseEntity(Usuario, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        if(StringUtils.isBlank(usuarioDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuarioDto.getApellido1()))
            return new ResponseEntity(new Mensaje("el primer apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuarioDto.getDni()))
            return new ResponseEntity(new Mensaje("el dni es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Usuario Usuario = new Usuario(usuarioDto.getNombre(), usuarioDto.getApellido1(), usuarioDto.getApellido2(), usuarioDto.getDni(), usuarioDto.getCalle(),
        		usuarioDto.getNumCalle(), usuarioDto.getTelefono1(), usuarioDto.getFechaNac(), usuarioDto.getLocalidad(),usuarioDto.getProvincia(), usuarioDto.getCodigoPostal(),
        		usuarioDto.getNombreUsuario(), usuarioDto.getEmail(), usuarioDto.getPassword(), usuarioDto.getRoles(),usuarioDto.getRolPrincipal(), usuarioDto.getLibros());
       
        usuarioService.save(Usuario);
        
        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.OK);
    }

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UsuarioDto usuarioDto) {
		if (!usuarioService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		if (usuarioService.existsByNombre(usuarioDto.getNombre())
				&& usuarioService.getByNombre(usuarioDto.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(usuarioDto.getNombre()))
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Usuario usuario = usuarioService.getOne(id).get();
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setApellido1(usuarioDto.getApellido1());
		usuario.setApellido2(usuarioDto.getApellido2());
		usuario.setCalle(usuarioDto.getCalle());
		usuario.setDni(usuarioDto.getDni());
		usuario.setNumCalle(usuarioDto.getNumCalle());
		usuario.setFechaNac(usuarioDto.getFechaNac());
		usuario.setTelefono1(usuarioDto.getTelefono1());
		usuario.setLocalidad(usuarioDto.getLocalidad());
		usuario.setProvincia(usuarioDto.getProvincia());
		usuario.setCodigoPostal(usuarioDto.getCodigoPostal());
		usuarioService.save(usuario);
		return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!usuarioService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		usuarioService.delete(id);
		return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
	}

}
