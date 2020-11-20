package com.aularuix.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.aularuix.back.enums.Reservado;

@Entity
public class Hora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int hora;
	private Reservado reservado;
	private String usuarioReserva;
	private String nombreZona;

	public Hora() {
		super();
	}

	public Hora(int hora, Reservado reservado, String usuarioReserva, String nombreZona) {
		super();
		this.hora = hora;
		this.reservado = reservado;
		this.usuarioReserva = usuarioReserva;
		this.nombreZona = nombreZona;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public Reservado getReservado() {
		return reservado;
	}

	public void setReservado(Reservado reservado) {
		this.reservado = reservado;
	}

	public String getUsuarioReserva() {
		return usuarioReserva;
	}

	public void setUsuarioReserva(String usuarioReserva) {
		this.usuarioReserva = usuarioReserva;
	}

	public String getNombreZona() {
		return nombreZona;
	}

	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	
	
}
