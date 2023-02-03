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
	
	@Column(name="vendido")
	private boolean vendido = false;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "usuario_vendedor", nullable = true)
	private Usuario usuarioVendedor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "usuario_comprador", nullable = true)
	private Usuario usuarioComprador;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "categoria", nullable = true)
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
	
	


	



	public Articulo(Long id, String titulo, int likes, String descripcion, float precio, String estado, boolean vendido,
			Usuario usuarioVendedor, Usuario usuarioComprador, Categoria categoria, Set<Foto> fotos) {
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
		this.fotos = fotos;
	}

	public Articulo() {
		super();
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", titulo=" + titulo + ", likes=" + likes + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", estado=" + estado + ", vendido=" + vendido + ", usuarioVendedor="
				+ usuarioVendedor + ", usuarioComprador=" + usuarioComprador + ", categoria=" + categoria + ", fotos="
				+ fotos + "]";
	}	
	
}
