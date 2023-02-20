package com.sim.mvc.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
	
@Entity
@Table(name="simarropop.articulo")
public class Articulo implements Serializable {
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
	private boolean vendido = false;
	
	@ManyToOne
	@JoinColumn(name = "usuario_vendedor", nullable = true)
	private Usuario usuarioVendedor;
	
	@ManyToOne
	@JoinColumn(name = "usuario_comprador", nullable = true)
	private Usuario usuarioComprador;
	
	@ManyToOne
	@JoinColumn(name = "categoria", nullable = true)
	private Categoria categoria;



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
	
	public Articulo(Articulo2 articulo2) {
		this.id = articulo2.getId();
		this.titulo = articulo2.getTitulo();
		this.likes = articulo2.getLikes();
		this.descripcion = articulo2.getDescripcion();
		this.precio = articulo2.getPrecio();
		this.estado = articulo2.getEstado();
		this.vendido = articulo2.getVendido();
		this.usuarioVendedor = articulo2.getUsuarioVendedor();
		this.usuarioComprador = articulo2.getUsuarioComprador();
		this.categoria = articulo2.getCategoria();
	}
	
	


	public Articulo(Long id, String titulo, int likes, String descripcion, float precio, String estado, boolean vendido,
			Usuario usuarioVendedor, Usuario usuarioComprador, Categoria categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.likes = likes;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.vendido = vendido;
		this.usuarioVendedor = usuarioVendedor;
		this.usuarioComprador = usuarioComprador;
		this.categoria = categoria;
	}

	public Articulo() {
		super();
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", titulo=" + titulo + ", likes=" + likes + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", estado=" + estado + ", vendido=" + vendido + ", usuarioVendedor="
				+ usuarioVendedor + ", usuarioComprador=" + usuarioComprador + ", categoria=" + categoria + "]";
	}





	
}
