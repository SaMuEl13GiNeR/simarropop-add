package com.sim.mvc.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sim.mvc.models.dao.ICategoriaDAO;
import com.sim.mvc.models.entity.Categoria;

@Service
@Qualifier("CategoriaServiceImpl")
public class CategoriaServiceImpl implements ICategoriaService{

	@Autowired
	private ICategoriaDAO categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaDao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
		return categoriaDao.findById(id).orElse(null);
	}

}
