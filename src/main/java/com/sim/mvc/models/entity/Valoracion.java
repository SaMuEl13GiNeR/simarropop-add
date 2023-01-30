package com.sim.mvc.models.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="valoracion")
public class Valoracion implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="estrellas")
	private int estrellas;
	
	@Column(name="opinion")
	private String opinion;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id_valoracion", nullable = false)
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Valoracion(Long id, int estrellas, String opinion, Usuario usuario) {
		super();
		this.id = id;
		this.estrellas = estrellas;
		this.opinion = opinion;
		this.usuario = usuario;
	}

	public Valoracion(int estrellas, String opinion, Usuario usuario) {
		super();
		this.estrellas = estrellas;
		this.opinion = opinion;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Valoracion [id=" + id + ", estrellas=" + estrellas + ", opinion=" + opinion + ", usuario=" + usuario
				+ "]";
	}
	
	
	
	

}
