package com.aularuix.back.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.aularuix.back.security.entity.Usuario;

public class DietaDto {

	private String nombreDieta;
	private List<Usuario> usuarios = new ArrayList<>();

	public DietaDto() {
		super();
	}

	public DietaDto(String nombreDieta, List<Usuario> usuarios) {
		super();
		this.nombreDieta = nombreDieta;
		this.usuarios = usuarios;
	}

	public String getNombreDieta() {
		return nombreDieta;
	}

	public void setNombreDieta(String nombreDieta) {
		this.nombreDieta = nombreDieta;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
}
