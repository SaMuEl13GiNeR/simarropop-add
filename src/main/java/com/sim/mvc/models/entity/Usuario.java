package com.sim.mvc.models.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="ubicacion")
	private String ubicacion;
	
	@Column(name="contrasenya")
	private String contrasenya;
	
	@Column(name="avatar")
	private String avatar;
	
    @OneToMany(mappedBy="usuario")
    private Set<Articulo> articulos;
    
    @OneToMany(mappedBy="usuario")
    private Set<Valoracion> valoraciones;
    
    @OneToMany(mappedBy="usuario")
    private Set<Mensaje> mensajes;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	public Set<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(Set<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Set<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Set<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Usuario(Long id, String nombre, String apellidos, String ubicacion, String contrasenya, String avatar,
			Set<Articulo> articulos, Set<Valoracion> valoraciones, Set<Mensaje> mensajes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ubicacion = ubicacion;
		this.contrasenya = contrasenya;
		this.avatar = avatar;
		this.articulos = articulos;
		this.valoraciones = valoraciones;
		this.mensajes = mensajes;
	}

	public Usuario(String nombre, String apellidos, String ubicacion, String contrasenya, String avatar,
			Set<Articulo> articulos, Set<Valoracion> valoraciones, Set<Mensaje> mensajes) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ubicacion = ubicacion;
		this.contrasenya = contrasenya;
		this.avatar = avatar;
		this.articulos = articulos;
		this.valoraciones = valoraciones;
		this.mensajes = mensajes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ubicacion=" + ubicacion
				+ ", contrasenya=" + contrasenya + ", avatar=" + avatar + ", articulos=" + articulos + ", valoraciones="
				+ valoraciones + ", mensajes=" + mensajes + "]";
	}
    
    
    
    
}
