package com.aularuix.back.controller;

import java.time.LocalDate;
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
import com.aularuix.back.dto.LibroDto;
import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.dto.UsuarioDto;
import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.entity.Libro;
import com.aularuix.back.enums.Alquilado;
import com.aularuix.back.enums.Estado;
import com.aularuix.back.enums.Reservado;
import com.aularuix.back.security.entity.Usuario;
import com.aularuix.back.security.service.UsuarioService;
import com.aularuix.back.service.ZonaComunService;
import com.aularuix.back.service.HoraService;

@RestController
@RequestMapping("/hora")
@CrossOrigin(origins = "*")
public class HoraController {

	@Autowired
    HoraService horaService;
	
	@GetMapping("/horas")
	public ResponseEntity<List<Hora>> list() {
		List<Hora> list = horaService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

}
