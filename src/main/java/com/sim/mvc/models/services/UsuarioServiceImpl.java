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
		return (List<Usuario>) usuarioDao.findAllUsuarios();
	}

	@Override
	public Usuario save(Usuario usuario) {
		usuario.setUser(true);
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
		usuario.setUser(true);
		return usuarioDao.save(usuario);
	}

	@Override
	public List<Usuario> findByNombre(String nombre) {
		return usuarioDao.findByNombre(nombre);
	}

	@Override
	public boolean validar(Usuario usuario) {
		Usuario usuarioBuscado = usuarioDao.findByCorreo(usuario.getCorreo());
		if(usuarioBuscado != null && usuario.getCorreo().equals(usuarioBuscado.getCorreo()) && usuario.getContrasenya().equals(usuarioBuscado.getContrasenya())) {
			return true;
		} else {
			return false;
		}
	}

}
