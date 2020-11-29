package com.aularuix.back.dto;

import java.util.ArrayList;
import java.util.List;

import com.aularuix.back.entity.Dieta;

public class ComedorDto {
	
	private List<Dieta> dietas = new ArrayList<>();
	private int capacidadComedor;
	
	public ComedorDto() {
		super();
	}

	public ComedorDto(List<Dieta> dietas, int capacidadComedor) {
		super();
		this.dietas = dietas;
		this.capacidadComedor = capacidadComedor;
	}

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	public int getCapacidadComedor() {
		return capacidadComedor;
	}

	public void setCapacidadComedor(int capacidadComedor) {
		this.capacidadComedor = capacidadComedor;
	}
	
}
