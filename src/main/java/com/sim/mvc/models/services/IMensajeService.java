package com.sim.mvc.models.services;

import java.util.List;

import com.sim.mvc.models.entity.Mensaje;

public interface IMensajeService {
	
	public List<Mensaje> findAll();
	
	public Mensaje save(Mensaje mensaje);
	
	public Mensaje findById(Long id);
	
	public void delete(Long id);
	
	public Mensaje update(Mensaje mensaje);
	
	public List<Mensaje> getMensajesInCommon(Long idEmisor, Long idReceptor);

	public List<Mensaje> getUsuarioMensajesInCommon(Long id);

}
