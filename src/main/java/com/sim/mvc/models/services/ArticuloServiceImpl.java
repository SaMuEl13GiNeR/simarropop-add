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
	public List<Articulo> findByUsuarioVendedorId(Long id) {
		return articuloDao.findByUsuarioVendedorId(id);
	}
	
	@Override
	@Transactional
	public List<Articulo> findByUsuarioVendedorIdVendido(Long id) {
		return articuloDao.findByUsuarioVendedorIdVendido(id);
	}
	
	@Override
	@Transactional
	public List<Articulo> findByUsuarioVendedorIdNoVendido(Long id) {
		return articuloDao.findByUsuarioVendedorIdNoVendido(id);
	}
	
	@Override
	@Transactional
	public List<Articulo> findByUsuarioCompradorId(Long id) {
		return articuloDao.findByUsuarioCompradorId(id);
	}

	@Override
	public List<Articulo> findByCategoriaId(Long id) {
		return articuloDao.findByCategoriaId(id);
	}

	@Override
	public List<Articulo> findAllOrderByLowerPrecio(Long id) {
		return articuloDao.findAllOrderByLowerPrecio(id);
	}

	@Override
	public List<Articulo> findAllOrderByHigherPrecio(Long id) {
		return articuloDao.findAllOrderByHigherPrecio(id);
	}

	@Override
	public List<Articulo> findAllOrderByTituloASC(Long id) {
		return articuloDao.findAllOrderByTituloASC(id);
	}
	
	@Override
	public List<Articulo> findAllOrderByTituloDESC(Long id) {
		return articuloDao.findAllOrderByTituloDESC(id);
	}
	
	@Override
	public List<Articulo> findByTitulo(String titulo) {
		return articuloDao.findByTitulo(titulo);
	}

	@Override
	public Articulo update(Articulo articulo) {
		return articuloDao.save(articulo);
	}

	@Override
	public List<Articulo> findAllAlien(Long id) {
		return articuloDao.findAllAlien(id);
	}

	@Override
	public List<Articulo> findAllAlienNoVendidos(Long id) {
		return articuloDao.findAllAlienNoVendidos(id);
	}

	@Override
	public List<Articulo> findAllVendidos(Long id) {
		return articuloDao.findAllUsuarioVendidos(id);
	}

	@Override
	public List<Articulo> findByAjenoTitulo(Long id, String titulo) {
		return articuloDao.findByTituloAjenos(id, titulo);
	}
	

}
