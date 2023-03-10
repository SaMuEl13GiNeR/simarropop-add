package com.sim.mvc.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="simarropop_valoracion")
public class Valoracion implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="estrellas")
	private int estrellas;
	
	@Column(name="opinion")
	private String opinion;
	
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

	public Valoracion(Long id, int estrellas, String opinion, Usuario usuarioEmisor, Usuario usuarioReceptor) {
		super();
		this.id = id;
		this.estrellas = estrellas;
		this.opinion = opinion;
		this.usuarioEmisor = usuarioEmisor;
		this.usuarioReceptor = usuarioReceptor;
	}

	public Valoracion(int estrellas, String opinion, Usuario usuarioEmisor, Usuario usuarioReceptor) {
		super();
		this.estrellas = estrellas;
		this.opinion = opinion;
		this.usuarioEmisor = usuarioEmisor;
		this.usuarioReceptor = usuarioReceptor;
	}

	public Valoracion() {
		super();
	}

	@Override
	public String toString() {
		return "Valoracion [id=" + id + ", estrellas=" + estrellas + ", opinion=" + opinion + ", usuarioEmisor="
				+ usuarioEmisor + ", usuarioReceptor=" + usuarioReceptor + "]";
	}



	
	
	
	

}
