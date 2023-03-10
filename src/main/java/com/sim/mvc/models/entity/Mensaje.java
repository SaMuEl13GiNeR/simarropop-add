package com.sim.mvc.models.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name="simarropop_mensaje")
public class Mensaje implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="contenido")
	private String contenido;
	
	@Column(name="hora")
	private java.sql.Timestamp hora;
	
	@ManyToOne
	@JoinColumn(name = "usuario_emisor")
	private Usuario usuarioEmisor;
	
	@ManyToOne
	@JoinColumn(name = "usuario_receptor")
	private Usuario usuarioReceptor;

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

	public Usuario getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public void setUsuarioEmisor(Usuario usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}

	public Usuario getUsuarioReceptor() {
		return usuarioReceptor;
	}

	public void setUsuarioReceptor(Usuario usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}

	public Mensaje(Long id, String contenido, Timestamp hora, Usuario usuarioEmisor, Usuario usuarioReceptor) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.hora = hora;
		this.usuarioEmisor = usuarioEmisor;
		this.usuarioReceptor = usuarioReceptor;
	}

	public Mensaje(String contenido, Timestamp hora, Usuario usuarioEmisor, Usuario usuarioReceptor) {
		super();
		this.contenido = contenido;
		this.hora = hora;
		this.usuarioEmisor = usuarioEmisor;
		this.usuarioReceptor = usuarioReceptor;
	}

	public Mensaje() {
		super();
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", contenido=" + contenido + ", hora=" + hora + ", usuarioEmisor=" + usuarioEmisor
				+ ", usuarioReceptor=" + usuarioReceptor + "]";
	}



	
	
	
	
	
}
