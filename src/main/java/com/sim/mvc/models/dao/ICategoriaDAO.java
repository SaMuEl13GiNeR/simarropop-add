package com.sim.mvc.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.sim.mvc.models.entity.Categoria;

public interface ICategoriaDAO extends CrudRepository<Categoria, Long> {

}
