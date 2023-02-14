package com.sim.mvc.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sim.mvc.models.dao.IArticuloDAO2;
import com.sim.mvc.models.entity.Articulo2;

@Service
@Qualifier("ArticuloServiceImpl2")
public class ArticuloServiceImpl2 implements IArticuloService2{

	@Autowired
	private IArticuloDAO2 articuloDao;
	
	

	@Override
	@Transactional
	public Articulo2 save(Articulo2 articulo) {
		return articuloDao.save(articulo);
	}


	

}
