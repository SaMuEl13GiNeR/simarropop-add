package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Valoracion;

public interface IValoracionService {
	public List<Valoracion> findAll();
	
	public Valoracion save(Valoracion valoracion);
	
	public Valoracion findById(Long id);
	
	public void delete(Long id);
	
	public Valoracion update(Valoracion valoracion);
	
	public float mediaUsuario(Long id);
	

}
