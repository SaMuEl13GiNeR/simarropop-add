package com.sim.mvc.models.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="res.partner")
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="ubicacion")
	private String ubicacion;
	
	@Column(name="contrasenya")
	private String contrasenya;
	
	@Column(name="correo")
	private String correo;
	
	@JsonIgnore
	@Column(name="is_user")
	private boolean isUser;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public boolean getUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Usuario() {
		super();
	}
	

	public Usuario(Long id, String nombre, String apellidos, String ubicacion, String contrasenya, String correo, boolean isUser) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ubicacion = ubicacion;
		this.contrasenya = contrasenya;
		this.correo = correo;
		this.isUser = isUser;
	}
	
	public Usuario(Usuario2 usuario) {
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.ubicacion = usuario.getUbicacion();
		this.contrasenya = usuario.getContrasenya();
		this.correo = usuario.getCorreo();
		this.isUser = usuario.getUser();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ubicacion=" + ubicacion
				+ ", contrasenya=" + contrasenya + ", correo=" + correo + "]";
	}    
    
}
