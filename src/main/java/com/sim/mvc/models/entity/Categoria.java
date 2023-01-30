package com.sim.mvc.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo")
	private String tipo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "articulo_id", nullable = false)
	private Articulo articulo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Categoria(Long id, String tipo, Articulo articulo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.articulo = articulo;
	}

	public Categoria(String tipo, Articulo articulo) {
		super();
		this.tipo = tipo;
		this.articulo = articulo;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", tipo=" + tipo + ", articulo=" + articulo + "]";
	}
	
	
	
}
