package com.sim.mvc.models.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@Table(name="simarropop_categoria")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Categoria implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo")
	private String tipo;
	
	@OneToMany(mappedBy="categoria")
    @JsonBackReference(value="articulos")
	private Set<Articulo> articulos;

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

	

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

	
	public Categoria(Long id, String tipo, Set<Articulo> articulos) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.articulos = articulos;
	}

	public Categoria() {
		super();
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", tipo=" + tipo + ", articulos=" + articulos + "]";
	}


	
	
	
}
