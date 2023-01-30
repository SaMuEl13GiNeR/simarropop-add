package com.sim.mvc.models.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="mensaje")
public class Mensaje implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="contenido")
	private String contenido;
	
	@Column(name="hora")
	private java.sql.Timestamp hora;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public java.sql.Timestamp getHora() {
		return hora;
	}

	public void setHora(java.sql.Timestamp hora) {
		this.hora = hora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Mensaje(Long id, String contenido, Timestamp hora, Usuario usuario) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.hora = hora;
		this.usuario = usuario;
	}

	public Mensaje(String contenido, Timestamp hora, Usuario usuario) {
		super();
		this.contenido = contenido;
		this.hora = hora;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", contenido=" + contenido + ", hora=" + hora + ", usuario=" + usuario + "]";
	}
	
	
	
	
}
