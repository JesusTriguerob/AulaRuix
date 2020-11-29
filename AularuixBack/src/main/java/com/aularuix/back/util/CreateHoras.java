package com.aularuix.back.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.enums.Reservado;
import com.aularuix.back.service.ZonaComunService;
import com.aularuix.back.service.HoraService;

@Component
public class CreateHoras implements CommandLineRunner {

//	@Autowired
//	ZonaComunService aulaService;
//	
//	@Autowired
//	HoraService horaService;
	
	@Override
	public void run(String... args) throws Exception {
//
//		Hora primera = new Hora(1, Reservado.DISPONIBLE, null);
//		Hora segunda = new Hora(2, Reservado.DISPONIBLE, null);
//		Hora tercera = new Hora(3, Reservado.DISPONIBLE, null);
//		Hora cuarta = new Hora(4, Reservado.DISPONIBLE, null);
//		Hora quinta = new Hora(5, Reservado.DISPONIBLE, null);
//		Hora sexta = new Hora(6, Reservado.DISPONIBLE, null);
//
//		horaService.save(primera);
//		horaService.save(segunda);
//		horaService.save(tercera);
//		horaService.save(cuarta);
//		horaService.save(quinta);
//		horaService.save(sexta);
	}

}
