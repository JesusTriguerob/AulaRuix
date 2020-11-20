package com.aularuix.back.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.aularuix.back.entity.Libro;
import com.aularuix.back.security.entity.Rol;

public class UsuarioDto {

    @NotBlank
	private String nombre;
    @NotBlank
    private String apellido1;
    private String apellido2;
    @NotBlank
    private String dni;    
    private String calle;  
    private String numCalle;       
    private String telefono1;       
	private Date fechaNac;		
    private String localidad;       
    private String provincia;   
    private String codigoPostal;
    private String nombreUsuario;
    private String password;
    private String email;
    private List<Rol> roles = new ArrayList();
    private String rolPrincipal;
    private List<Libro> libros = new ArrayList();
    
	public UsuarioDto() {
	}

	public UsuarioDto(@NotBlank String nombre, @NotBlank String apellido1, String apellido2, @NotBlank String dni,
			String calle, String numCalle, String telefono1, Date fechaNac, String localidad, String provincia,
			String codigoPostal, String nombreUsuario, String password, String email, List<Rol> roles, String rolPrincipal,
			List<Libro> libros) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.calle = calle;
		this.numCalle = numCalle;
		this.telefono1 = telefono1;
		this.fechaNac = fechaNac;
		this.localidad = localidad;
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.rolPrincipal = rolPrincipal;
		this.libros = libros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumCalle() {
		return numCalle;
	}

	public void setNumCalle(String numCalle) {
		this.numCalle = numCalle;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getRolPrincipal() {
		return rolPrincipal;
	}

	public void setRolPrincipal(String rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	
    
}
