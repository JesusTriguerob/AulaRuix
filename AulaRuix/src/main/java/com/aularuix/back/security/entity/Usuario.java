package com.aularuix.back.security.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

@Entity
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nombre;

    private String apellido1;
    
    private String apellido2;
    
    private String dni;    
    
    private String calle;  
    
    @Column(name = "num_calle")
    private String numCalle;    
    
    private String telefono1;   
    
	@Column(name="fecha_nac")
	@Temporal(TemporalType.DATE)
	private Date fechaNac;	
	
    private String localidad;   
    
    private String provincia;   
    
    @Column(name="codigo_postal")
    private String codigoPostal;
    
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles = new ArrayList<>();

    private String rolPrincipal;
    
    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }
   
	public Usuario(@NotNull String nombre, String apellido1, String apellido2, String dni, String calle,
			String numCalle, String telefono1, Date fechaNac, String localidad, String provincia, String codigoPostal,
			@NotNull String nombreUsuario, @NotNull String email, @NotNull String password, @NotNull List<Rol> roles, String rolPrincipal) {
		super();
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
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.rolPrincipal = rolPrincipal;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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

	public String getRolPrincipal() {
		return rolPrincipal;
	}

	public void setRolPrincipal(String rolPrincipal) {
		this.rolPrincipal = rolPrincipal;
	}
    
	
}
