package com.sim.mvc.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sim.mvc.models.dao.IUsuarioDAO;
import com.sim.mvc.models.entity.Usuario;

@Service
@Qualifier("UsuarioServiceImpl")
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioDAO usuarioDao;

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public List<Usuario> findByNombre(String nombre) {
		return usuarioDao.findByNombre(nombre);
	}

}
