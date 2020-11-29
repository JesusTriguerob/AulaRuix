package com.aularuix.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.aularuix.back.entity.ZonaComun;
import com.aularuix.back.entity.Hora;
import com.aularuix.back.enums.Reservado;
import com.aularuix.back.service.ZonaComunService;
import com.aularuix.back.service.HoraService;

@SpringBootApplication
@EnableScheduling
public class AularuixBackApplication {

	@Autowired
	HoraService horaService;
	@Autowired
	ZonaComunService aulaService;
	
	public static void main(String[] args) {
		SpringApplication.run(AularuixBackApplication.class, args);
	}
	
	@Scheduled(fixedRate = 86400000)
    public void LimpiarHorario() {
        
		//se lanza el monitor cada 24 horas, para limpiar la tabla del horario 
		//y poner todas las zonas disponibles
		List<ZonaComun> zonas = aulaService.list();
		
		for (ZonaComun aula : zonas) {
			ZonaComun zona = aulaService.getOne(aula.getId()).get();
			
			List<Hora> horas = zona.getHoras();
			for (Hora hora : horas) {
				hora.setReservado(Reservado.DISPONIBLE);
				hora.setUsuarioReserva(null);
				horaService.save(hora);
			}
			zona.setHoras(horas);
			aulaService.save(aula);
		}
    }

}
