package com.sim.mvc.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="foto")
public class Foto implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "imagen")
	private String imagen;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo articulo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Foto(Long id, String imagen, Articulo articulo) {
		super();
		this.id = id;
		this.imagen = imagen;
		this.articulo = articulo;
	}

	public Foto(String imagen, Articulo articulo) {
		super();
		this.imagen = imagen;
		this.articulo = articulo;
	}

	@Override
	public String toString() {
		return "Foto [id=" + id + ", imagen=" + imagen + ", articulo=" + articulo + "]";
	}
	
	
	
	
	
}
