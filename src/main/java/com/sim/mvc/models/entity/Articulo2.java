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
	
	
	
	@Column(name="date_order")
	private java.sql.Timestamp dateOrder;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="partner_invoice_id")
	private int partner_invoice_id; 
	
	@Column(name="partner_shipping_id")
	private int partner_shipping_id; 
	
	@Column(name="pricelist_id")
	private int pricelist_id; 
	
	@Column(name="partner_id")
	private int partnerID; 
	
	@Column(name="company_id")
	private int company_id; 
	
	
	
	
	
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

	@JsonIgnore
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


	public Articulo2() {
		super();
	}

	
	public int getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public java.sql.Timestamp getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(java.sql.Timestamp dateOrder) {
		this.dateOrder = dateOrder;
	}

	public int getPartner_invoice_id() {
		return partner_invoice_id;
	}

	public void setPartner_invoice_id(int partner_invoice_id) {
		this.partner_invoice_id = partner_invoice_id;
	}

	public int getPartner_shipping_id() {
		return partner_shipping_id;
	}

	public void setPartner_shipping_id(int partner_shipping_id) {
		this.partner_shipping_id = partner_shipping_id;
	}

	public int getPricelist_id() {
		return pricelist_id;
	}

	public void setPricelist_id(int pricelist_id) {
		this.pricelist_id = pricelist_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "Articulo2 [id=" + id + ", titulo=" + titulo + ", likes=" + likes + ", dateOrder=" + dateOrder
				+ ", name=" + name + ", partner_invoice_id=" + partner_invoice_id + ", partner_shipping_id="
				+ partner_shipping_id + ", pricelist_id=" + pricelist_id + ", partnerID=" + partnerID + ", company_id="
				+ company_id + ", descripcion=" + descripcion + ", precio=" + precio + ", estado=" + estado
				+ ", vendido=" + vendido + ", usuarioVendedor=" + usuarioVendedor + ", usuarioComprador="
				+ usuarioComprador + ", categoria=" + categoria + ", fotos=" + fotos + "]";
	}
















	
}
