package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Foto;


public interface IFotoService {
	
	public Foto save(Foto foto);
	
	public Foto findById(Long id);
	
	public void delete(Long id);
	
	public List<Foto> findByAriculoId(Long id);
}
