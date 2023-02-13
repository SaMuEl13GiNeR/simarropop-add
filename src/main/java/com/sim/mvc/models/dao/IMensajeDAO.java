package com.sim.mvc.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sim.mvc.models.entity.Mensaje;

public interface IMensajeDAO extends CrudRepository<Mensaje, Long> {
	@Query("FROM Mensaje m WHERE m.usuarioEmisor.id = :idEmisor AND m.usuarioReceptor.id = :idReceptor ORDER BY m.hora")
	List<Mensaje> findMensajeFromTo(@Param("idEmisor") Long idEmisor, @Param("idReceptor") Long idReceptor);
	
	@Query("FROM Mensaje m WHERE m.usuarioEmisor.id = :id OR m.usuarioReceptor.id = :id")
	List<Mensaje> getUsuarioMensajesInCommon(@Param("id") Long id);
	
}
