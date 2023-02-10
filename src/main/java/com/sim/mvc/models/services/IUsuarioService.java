package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	
	public Usuario save(Usuario usuario);
	
	public Usuario findById(Long id);
	
	public void delete(Long id);
	
	public Usuario update(Usuario usuario);
	
	public List<Usuario> findByNombre(String nombre);
	
	public boolean validar(Usuario usuario);
}
