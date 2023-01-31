package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Articulo;

public interface IArticuloService {
	
	public List<Articulo> findAll();
	
	public Articulo save(Articulo articulo);
	
	public Articulo findById(Long id);
	
	public void delete(Long id);
	
	public List<Articulo> findByUsuarioId(Long id);
	
	public List<Articulo> findByCategoriaId(Long id);
	
	public List<Articulo> findAllOrderByLowerPrecio();
	
	public List<Articulo> findAllOrderByHigherPrecio();
	
	public Articulo findByTitulo(String titulo);
	
	public Articulo update(Articulo articulo);
}
