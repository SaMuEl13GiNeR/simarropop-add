package com.sim.mvc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sim.mvc.models.entity.Articulo;
import com.sim.mvc.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
	@Query("FROM Usuario u WHERE u.nombre LIKE :nombre")
	List<Usuario> findByNombre(@Param("nombre") String nombre);
}