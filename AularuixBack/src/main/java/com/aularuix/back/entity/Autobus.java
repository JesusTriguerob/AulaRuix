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

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.aularuix.back.security.entity.Usuario;

import net.bytebuddy.implementation.bind.annotation.Default;

@Entity
public class Autobus {

	private static int VEINTICINTO = 25;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany
    @JoinTable(name = "autobus_usuarios", joinColumns = @JoinColumn(name = "autobus_id"),
    inverseJoinColumns = @JoinColumn(name = "usuario_id"))	
	private List<Usuario> usuarios = new ArrayList<>();
	
	private int capacidadAutobus;
	
	
	public Autobus() {
		super();
	}


	public Autobus(List<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
		this.capacidadAutobus = VEINTICINTO;
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