package com.sim.mvc.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.sim.mvc.models.entity.Mensaje;

public interface IMensajeDAO extends CrudRepository<Mensaje, Long> {

}
