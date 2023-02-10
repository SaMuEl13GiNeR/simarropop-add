package com.sim.mvc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sim.mvc.models.entity.Valoracion;

public interface IValoracionDAO extends JpaRepository<Valoracion, Long> {
	@Query("FROM Valoracion a WHERE a.usuarioReceptor.id != :id")
	List<Valoracion> findByUsuarioReceptor(@Param("id") Long id);
}
