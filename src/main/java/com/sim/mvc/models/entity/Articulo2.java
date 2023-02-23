package com.sim.mvc.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
	
@Entity
@Table(name="simarropop.articulo")
public class Articulo2 implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="likes")
	private int likes; 
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precio")
	private float precio;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="vendido")
	private boolean vendido;
	
	@ManyToOne
	@JoinColumn(name = "usuario_vendedor", nullable = true)
	private Usuario usuarioVendedor;
	
	@ManyToOne
	@JoinColumn(name = "usuario_comprador", nullable = true)
	private Usuario usuarioComprador;
	
	@ManyToOne
	@JoinColumn(name = "categoria", nullable = true)
	private Categoria categoria;

	@Column(name="foto")
    private String foto;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Usuario getUsuarioVendedor() {
		return usuarioVendedor;
	}

	public void setUsuarioVendedor(Usuario usuarioVendedor) {
		this.usuarioVendedor = usuarioVendedor;
	}

	public Usuario getUsuarioComprador() {
		return usuarioComprador;
	}

	public void setUsuarioComprador(Usuario usuarioComprador) {
		this.usuarioComprador = usuarioComprador;
	}
	
	public boolean getVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
	
	public Articulo2(Articulo articulo) {
		super();
		this.id = articulo.getId();
		this.titulo = articulo.getTitulo();
		this.likes = articulo.getLikes();
		this.descripcion = articulo.getDescripcion();
		this.precio = articulo.getPrecio();
		this.estado = articulo.getEstado();
		this.vendido = articulo.getVendido();
		this.usuarioVendedor = articulo.getUsuarioVendedor();
		this.usuarioComprador = articulo.getUsuarioComprador();
		this.categoria = articulo.getCategoria();
	}


	public Articulo2() {
		super();
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", titulo=" + titulo + ", likes=" + likes + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", estado=" + estado + ", vendido=" + vendido + ", usuarioVendedor="
				+ usuarioVendedor + ", usuarioComprador=" + usuarioComprador + ", categoria=" + categoria + ", foto="
				+ foto + "]";
	}




	
}
