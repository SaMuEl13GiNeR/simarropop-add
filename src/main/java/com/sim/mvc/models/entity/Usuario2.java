package com.sim.mvc.models.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="res.partner")
public class Usuario2 implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="lat")
	private float lat;
	
	@Column(name="lon")
	private float lon;
	
	@Column(name="contrasenya")
	private String contrasenya;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="avatar")
	private String avatar;
	
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

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
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
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Usuario2() {
		super();
	}
	
	public Usuario2(Long id, String nombre, String apellidos, float lat, float lon, String contrasenya, String correo,
			String avatar, boolean isUser) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.lat = lat;
		this.lon = lon;
		this.contrasenya = contrasenya;
		this.correo = correo;
		this.avatar = avatar;
		this.isUser = isUser;
	}

	public Usuario2(Usuario usuario) {
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.lat = usuario.getLat();
		this.lon = usuario.getLon();
		this.contrasenya = usuario.getContrasenya();
		this.correo = usuario.getCorreo();
		this.isUser = usuario.getUser();
	}

	@Override
	public String toString() {
		return "Usuario2 [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", lat=" + lat + ", lon="
				+ lon + ", contrasenya=" + contrasenya + ", correo=" + correo + ", avatar=" + avatar + ", isUser="
				+ isUser + "]";
	}

	

  
    
}
