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

import com.aularuix.back.dto.AulaDto;
import com.aularuix.back.dto.LibroDto;
import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.dto.UsuarioDto;
import com.aularuix.back.entity.Aula;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.entity.Libro;
import com.aularuix.back.enums.Alquilado;
import com.aularuix.back.enums.Estado;
import com.aularuix.back.enums.Reservado;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.service.UsuarioService;
import com.aularuix.back.service.AulaService;
import com.aularuix.back.service.HoraService;

@RestController
@RequestMapping("/aula")
@CrossOrigin(origins = "*")
public class AulaController {
	
	@Autowired
	AulaService aulaService;
	
	@Autowired
	HoraService horaService;
	
	@Autowired
    UsuarioService usuarioService;
	
	@GetMapping("/aulas")
	public ResponseEntity<List<Aula>> list() {
		List<Aula> list = aulaService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody AulaDto aulaDto) {
		if (!aulaService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		
		Aula aula = aulaService.getOne(id).get();
		
		aulaService.save(aula);
		return new ResponseEntity(new Mensaje("Horario actualizado"), HttpStatus.OK);
	}
	
	@PostMapping("/reservar/{id}")
	public ResponseEntity<?> reservar(@PathVariable("id")int id,@RequestBody int hora){
		
		 if(!aulaService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        
	        Mensaje response = new Mensaje(null);
	        
	        Aula aula = aulaService.getOne(id).get();
	        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        Usuario usuario = usuarioService.getByNombreUsuario(userDetails.getUsername()).get();
			
	        List<Hora> horas = aula.getHoras();
	        
	        for (Hora hor : horas) {
				if (hor.getHora() == hora) {
					hor.setReservado(Reservado.RESERVADO);
					hor.setUsuarioReserva(usuario.getNombreUsuario());
				}
			}	

	        aula.setHoras(horas);
	        
	        aulaService.save(aula);
	        return new ResponseEntity(response, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AulaDto aulaDto){
        if(StringUtils.isBlank(aulaDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        List<Hora> horas = generarHoras(aulaDto.getNombre());
        Aula aula = new Aula(aulaDto.getNombre(), horas);
        		aulaService.save(aula);
        return new ResponseEntity(new Mensaje("Zona creada"), HttpStatus.OK);
    }
	
	private List<Hora> generarHoras(String nombreZona){
		List<Hora> horas = new ArrayList<Hora>();
		Hora primera = new Hora(1, Reservado.DISPONIBLE, null,nombreZona);
		Hora segunda = new Hora(2, Reservado.DISPONIBLE, null,nombreZona);
		Hora tercera = new Hora(3, Reservado.DISPONIBLE, null,nombreZona);
		Hora cuarta = new Hora(4, Reservado.DISPONIBLE, null,nombreZona);
		Hora quinta = new Hora(5, Reservado.DISPONIBLE, null,nombreZona);
		Hora sexta = new Hora(6, Reservado.DISPONIBLE, null,nombreZona);
		
		horas.add(primera);
		horas.add(segunda);
		horas.add(tercera);
		horas.add(cuarta);
		horas.add(quinta);
		horas.add(sexta);
		
		horaService.save(primera);
		horaService.save(segunda);
		horaService.save(tercera);
		horaService.save(cuarta);
		horaService.save(quinta);
		horaService.save(sexta);
		
		return horas;
	}
}
