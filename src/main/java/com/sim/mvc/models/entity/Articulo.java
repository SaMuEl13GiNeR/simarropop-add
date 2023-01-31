package com.sim.mvc.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
	
@Entity
@Table(name="simarropop_articulo")
public class Articulo implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(name="likes")
	private int likes; 
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precio")
	private float precio;
	
	@Column(name="estado")
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "usuario_id", nullable = true)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "categorai_id", nullable = true)
	private Categoria categoria;

    @OneToMany(mappedBy="articulo")
    private Set<Foto> fotos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}

//	TODOS LOS ATRIBUTOS
	public Articulo(Long id, String titulo, int likes, String descripcion, float precio, String estado, Usuario usuario,
			Categoria categoria, Set<Foto> fotos) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.likes = likes;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.usuario = usuario;
		this.categoria = categoria;
		this.fotos = fotos;
	}

//	TODOS LOS CAMPOS MENOS ID
	public Articulo(String titulo, int likes, String descripcion, float precio, String estado, Usuario usuario,
			Categoria categoria, Set<Foto> fotos) {
		super();
		this.titulo = titulo;
		this.likes = likes;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.usuario = usuario;
		this.categoria = categoria;
		this.fotos = fotos;
	}

//	TODOS LOS CAMPOS MENOS LIKES
	public Articulo(Long id, String titulo, String descripcion, float precio, String estado, Usuario usuario,
			Categoria categoria, Set<Foto> fotos) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.usuario = usuario;
		this.categoria = categoria;
		this.fotos = fotos;
	}
	

	public Articulo(String titulo, String descripcion, float precio, String estado, Usuario usuario, Categoria categoria,
		Set<Foto> fotos) {
	super();
	this.titulo = titulo;
	this.descripcion = descripcion;
	this.precio = precio;
	this.estado = estado;
	this.usuario = usuario;
	this.categoria = categoria;
	this.fotos = fotos;
}

	public Articulo() {
	super();
}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", titulo=" + titulo + ", likes=" + likes + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", estado=" + estado + ", usuario=" + usuario + ", categoria=" + categoria
				+ ", fotos=" + fotos + "]";
	}
    
    
	

}
