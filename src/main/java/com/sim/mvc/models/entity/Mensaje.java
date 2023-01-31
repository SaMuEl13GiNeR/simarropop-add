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
	@JoinColumn(name = "usuario_id_emisor", nullable = false)
	private Usuario usuarioEmisor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id_receptor", nullable = false)
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
