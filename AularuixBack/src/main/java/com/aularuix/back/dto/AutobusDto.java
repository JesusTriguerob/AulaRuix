package com.aularuix.back.dto;

import java.util.ArrayList;
import java.util.List;

import com.aularuix.back.entity.Dieta;
import com.aularuix.back.security.entity.Usuario;

public class AutobusDto {
	
	private List<Usuario> usuarios = new ArrayList<>();
	private int capacidadAutobus;
	
	public AutobusDto() {
		super();
	}

	public AutobusDto(List<Usuario> usuarios, int capacidadAutobus) {
		super();
		this.usuarios = usuarios;
		this.capacidadAutobus = capacidadAutobus;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public int getCapacidadAutobus() {
		return capacidadAutobus;
	}

	public void setCapacidadAutobus(int capacidadAutobus) {
		this.capacidadAutobus = capacidadAutobus;
	}
	
}
