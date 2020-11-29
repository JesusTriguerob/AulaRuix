package com.aularuix.back.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aularuix.back.dto.ComedorDto;
import com.aularuix.back.dto.Mensaje;
import com.aularuix.back.entity.Comedor;
import com.aularuix.back.entity.Dieta;
import com.aularuix.back.service.ComedorService;
import com.aularuix.back.service.DietaService;

@RestController
@RequestMapping("/comedor")
@CrossOrigin(origins = "*")
public class ComedorController {

	@Autowired
    ComedorService comedorService;

	@Autowired
    DietaService dietaService;
	
	@GetMapping("/comedores")
	public ResponseEntity<List<Comedor>> list() {
		List<Comedor> list = comedorService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ComedorDto comedorDto){
      Comedor comedor = new Comedor(generarDietas());
        comedorService.save(comedor);
        return new ResponseEntity(new Mensaje("comedor creado"), HttpStatus.OK);
    }
	 
	@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ComedorDto comedorDto){
        
        Comedor comedor = comedorService.getOne(id).get();
        comedor.setDietas(comedorDto.getDietas());
        
        comedorService.save(comedor);
        return new ResponseEntity(new Mensaje("Comedor actualizado"), HttpStatus.OK);
    }

	private List<Dieta> generarDietas() {
		
		Dieta dieta1 = new Dieta("Sin Gluten", null);
		Dieta dieta2 = new Dieta("Sin Lactosa", null);
		Dieta dieta3 = new Dieta("Sin Proteinas", null);
		Dieta dieta4 = new Dieta("Normal", null);
		
		List<Dieta> dietas = new ArrayList<Dieta>();
		
		dietas.add(dieta1);
		dietas.add(dieta2);
		dietas.add(dieta3);
		dietas.add(dieta4);
		
		dietaService.save(dieta1);
		dietaService.save(dieta2);
		dietaService.save(dieta3);
		dietaService.save(dieta4);
		
		return dietas;
		
	}
}
