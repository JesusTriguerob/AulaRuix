package com.aularuix.back.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "aula_hora", joinColumns = @JoinColumn(name = "aula_id"),
    inverseJoinColumns = @JoinColumn(name = "hora_id"))
	private List<Hora> horas = new ArrayList();
	
	public Aula() {
		super();
	}

	public Aula(String nombre, List<Hora> horas) {
		super();
		this.nombre = nombre;
		this.horas = horas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
