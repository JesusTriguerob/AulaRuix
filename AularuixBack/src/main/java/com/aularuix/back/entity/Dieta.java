package com.aularuix.back.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.aularuix.back.security.entity.Usuario;

@Entity
public class Dieta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombreDieta;
	 
	@OneToMany
    @JoinTable(name = "dieta_usuario", joinColumns = @JoinColumn(name = "dieta_id"),
    inverseJoinColumns = @JoinColumn(name = "usuario_id"))	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public Dieta() {
		super();
	}
	
	public Dieta(String nombreDieta, List<Usuario> usuarios) {
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
