package com.sim.mvc.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sim.mvc.models.dao.IValoracionDAO;
import com.sim.mvc.models.entity.Valoracion;

@Service
@Qualifier("ValoracionServiceImpl")
public class ValoracionServiceImpl implements IValoracionService{

	@Autowired
	private IValoracionDAO valoracionDao;

	@Override
	public List<Valoracion> findAll() {
		return (List<Valoracion>) valoracionDao.findAll();
	}

	@Override
	public Valoracion save(Valoracion valoracion) {
		return valoracionDao.save(valoracion);
	}

	@Override
	@Transactional(readOnly = true)
	public Valoracion findById(Long id) {
		return valoracionDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		valoracionDao.deleteById(id);
	}

	@Override
	public Valoracion update(Valoracion valoracion) {
		return valoracionDao.save(valoracion);
	}

	@Override
	public float mediaUsuario(Long id) {
		float media = 0;
		List<Valoracion> valoracionesDeUsuario = valoracionDao.findByUsuarioReceptor(id);
		if (valoracionesDeUsuario == null) {
			return media;
		}
		for (int i = 0; i < valoracionesDeUsuario.size(); i++) {
			media += valoracionesDeUsuario.get(i).getEstrellas();
		}
		media/=valoracionesDeUsuario.size();
		return media;
	}

	
	
	
}
