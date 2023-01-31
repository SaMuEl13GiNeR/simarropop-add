package com.sim.mvc.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.sim.mvc.models.entity.Foto;

public interface IFotoDAO extends CrudRepository<Foto, Long> {

}
