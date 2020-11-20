package com.aularuix.back.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.aularuix.back.enums.Alquilado;
import com.aularuix.back.enums.Categoria;
import com.aularuix.back.enums.Estado;

public class LibroDto {

    @NotBlank
    private String nombre;
    private Categoria categoria;
    private Estado estado;
    private Alquilado alquilado;
    private String usuario;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;

    public LibroDto() {
    }

	public LibroDto(@NotBlank String nombre, Categoria categoria, Estado estado, Alquilado alquilado, String usuario,
			LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.estado = estado;
		this.alquilado = alquilado;
		this.usuario = usuario;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaDevolucion = fechaDevolucion;
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

	public Alquilado isAlquilado() {
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

	
}
