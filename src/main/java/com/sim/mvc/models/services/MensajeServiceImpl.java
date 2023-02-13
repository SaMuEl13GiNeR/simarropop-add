package com.sim.mvc.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sim.mvc.models.dao.IMensajeDAO;
import com.sim.mvc.models.entity.Mensaje;

@Service
@Qualifier("MensajeServiceImpl")
public class MensajeServiceImpl implements IMensajeService{

	@Autowired
	private IMensajeDAO mensajeDao;
	
	@Override
	public List<Mensaje> findAll() {
		return (List<Mensaje>) mensajeDao.findAll();
	}

	@Override
	public Mensaje save(Mensaje mensaje) {
		return mensajeDao.save(mensaje);
	}

	@Override
	public Mensaje findById(Long id) {
		return mensajeDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		mensajeDao.deleteById(id);	
	}

	@Override
	public Mensaje update(Mensaje mensaje) {
		return mensajeDao.save(mensaje);
	}

	@Override
	public List<Mensaje> getMensajesInCommon(Long idEmisor, Long idReceptor) {
		return mensajeDao.findMensajeFromTo(idEmisor, idReceptor);
	}

	@Override
	public List<Mensaje> getUsuarioMensajesInCommon(Long id) {
		return mensajeDao.getUsuarioMensajesInCommon(id).stream().distinct().collect(Collectors.toList());
	}

}
