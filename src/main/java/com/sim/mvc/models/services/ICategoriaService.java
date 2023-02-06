package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Categoria;


public interface ICategoriaService {
	
	public List<Categoria> findAll();
	public Categoria findById(Long id);

}
