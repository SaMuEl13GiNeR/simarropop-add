package com.sim.mvc.models.entity;

import java.io.Serializable;


import jakarta.persistence.*;

@Entity
@Table(name="simarropop_categoria")
public class Categoria implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo")
	private String tipo;
	
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
	
	public Categoria(Long id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Categoria() {
		super();
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", tipo=" + tipo + "]";
	}
	
}
