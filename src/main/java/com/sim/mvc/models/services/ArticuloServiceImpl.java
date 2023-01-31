package com.sim.mvc.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sim.mvc.models.dao.IArticuloDAO;
import com.sim.mvc.models.entity.Articulo;

@Service
@Qualifier("ArticuloServiceImpl")
public class ArticuloServiceImpl implements IArticuloService{

	@Autowired
	private IArticuloDAO articuloDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		return (List<Articulo>) articuloDao.findAll();
	}

	@Override
	@Transactional
	public Articulo save(Articulo articulo) {
		return articuloDao.save(articulo);
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		return articuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		articuloDao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Articulo> findByUsuarioId(Long id) {
		return articuloDao.findByUsuarioId(id);
	}

	@Override
	public List<Articulo> findByCategoriaId(Long id) {
		return articuloDao.findByCategoriaId(id);
	}

	@Override
	public List<Articulo> findAllOrderByLowerPrecio() {
		return articuloDao.findAllOrderByLowerPrecio();
	}

	@Override
	public List<Articulo> findAllOrderByHigherPrecio() {
		return articuloDao.findAllOrderByHigherPrecio();
	}

	@Override
	public Articulo findByTitulo(String titulo) {
		return articuloDao.findByTitulo(titulo);
	}

	@Override
	public Articulo update(Articulo articulo) {
		return articuloDao.save(articulo);
	}
	

}
