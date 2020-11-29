package com.aularuix.back.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.aularuix.back.entity.Hora;
import com.aularuix.back.enums.Reservado;

public class ZonaComunDto {

	 @NotBlank
	 private String nombre;
	 private List<Hora> horas = new ArrayList();
	 
	 public ZonaComunDto() {
		super();
	 }

	public ZonaComunDto(@NotBlank String nombre, List<Hora> horas) {
		super();
		this.nombre = nombre;
		this.horas = horas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Hora> getHoras() {
		return horas;
	}

	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}

	
}
