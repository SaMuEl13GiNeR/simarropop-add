package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Articulo;

public interface IArticuloService {
	
	public List<Articulo> findAll();
	
	public List<Articulo> findAllAlien(Long id);
	
	public List<Articulo> findAllAlienNoVendidos(Long id);
	
	public List<Articulo> findAllVendidos(Long id);
	
	public Articulo save(Articulo articulo);
	
	public Articulo findById(Long id);
	
	public void delete(Long id);
	
	public List<Articulo> findByUsuarioVendedorId(Long id);
	
	public List<Articulo> findByUsuarioVendedorIdVendido(Long id);
	
	public List<Articulo> findByUsuarioVendedorIdNoVendido(Long id);
	
	public List<Articulo> findByUsuarioCompradorId(Long id);
	
	public List<Articulo> findByCategoriaId(Long id);
	
	public List<Articulo> findAllOrderByLowerPrecio(Long id);
	
	public List<Articulo> findAllOrderByHigherPrecio(Long id);
	
	public List<Articulo> findAllOrderByTituloASC(Long id);
	
	public List<Articulo> findAllOrderByTituloDESC(Long id);
	
	public List<Articulo> findByTitulo(String titulo);
	
	public List<Articulo> findByAjenoTitulo(Long id, String titulo);
	
	public Articulo update(Articulo articulo);
}
