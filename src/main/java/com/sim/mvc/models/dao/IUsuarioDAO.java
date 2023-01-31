package com.sim.mvc.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.sim.mvc.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

}
