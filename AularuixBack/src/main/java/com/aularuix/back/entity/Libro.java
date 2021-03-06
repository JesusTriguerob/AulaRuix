package com.aularuix.back.entity;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.aularuix.back.enums.Alquilado;
import com.aularuix.back.enums.Categoria;
import com.aularuix.back.enums.Estado;

@Entity
public class Libro {
	
	private static int ZERO = 0;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private Categoria categoria;
    private Estado estado;
    private int vecesAlquilado;
    private Alquilado alquilado;
    private String usuario;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;

    public Libro() {
    }

	public Libro(String nombre, Categoria categoria, Estado estado, Alquilado alquilado, String usuario,
			LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.estado = estado;
		this.alquilado = alquilado;
		this.usuario = usuario;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaDevolucion = fechaDevolucion;
		this.vecesAlquilado = ZERO;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Alquilado getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(Alquilado alquilado) {
		this.alquilado = alquilado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	public void setFechaAlquiler(LocalDate fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public int getVecesAlquilado() {
		return vecesAlquilado;
	}

	public void setVecesAlquilado(int vecesAlquilado) {
		this.vecesAlquilado = vecesAlquilado;
	}

	
	
}
