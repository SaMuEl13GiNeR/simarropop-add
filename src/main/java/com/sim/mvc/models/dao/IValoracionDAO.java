package com.sim.mvc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sim.mvc.models.entity.Valoracion;

public interface IValoracionDAO extends CrudRepository<Valoracion, Long> {
	@Query("FROM Valoracion v WHERE v.usuarioReceptor.id = :id")
	List<Valoracion> findByUsuarioReceptor(@Param("id") Long id);
}
