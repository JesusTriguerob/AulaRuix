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
public class Comedor {

	private static int CINCUENTA = 50;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany
    @JoinTable(name = "comedor_dietas", joinColumns = @JoinColumn(name = "comedor_id"),
    inverseJoinColumns = @JoinColumn(name = "dieta_id"))	
	private List<Dieta> dietas = new ArrayList<>();
	
	private int capacidadComedor;
	
	
	public Comedor() {
		super();
	}


	public Comedor(List<Dieta> dietas) {
		super();
		this.dietas = dietas;
		this.capacidadComedor = CINCUENTA;
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