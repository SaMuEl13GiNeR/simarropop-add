package com.sim.mvc.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sim.mvc.models.entity.Mensaje;
import com.sim.mvc.models.entity.Usuario;
import com.sim.mvc.models.services.IMensajeService;

@CrossOrigin(origins = { "http://192.168.8.226:4200" })
@RestController
@RequestMapping("/simarropop")
public class MensajeRestController {
	
	@Autowired
	@Qualifier("MensajeServiceImpl")
	private IMensajeService mensajeService;
	
	@GetMapping("/mensajes")
	public List<Mensaje> findAll() {
		return mensajeService.findAll();
	}
	
	@GetMapping("/mensajes/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Mensaje mensaje = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			mensaje = mensajeService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(mensaje == null) {
			response.put("mensaje", "El mensaje ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
	}
	
	@PostMapping("/mensajes/nuevo")
	public ResponseEntity<?> create(@RequestBody Mensaje mensaje, BindingResult result) {
		mensaje.setHora(new Timestamp(System.currentTimeMillis()));
		
		Mensaje mensajeNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			mensajeNew = mensajeService.save(mensaje);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensajee", "El mensaje ha sido creado con éxito!");
		response.put("mensaje", mensajeNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/mensajes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			mensajeService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el mensaje de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Mensaje eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/mensajes/{id}")
	public ResponseEntity<?> update(@RequestBody Mensaje mensaje, BindingResult result, @PathVariable Long id) {
		
		Mensaje mensajeActual = mensajeService.findById(id);

		Mensaje mensajeUpdated = null;

		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (mensajeActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el mensaje ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			mensajeActual.setContenido(mensaje.getContenido());
			mensajeActual.setHora(mensaje.getHora());
			mensajeActual.setUsuarioEmisor(mensaje.getUsuarioEmisor());
			mensajeActual.setUsuarioReceptor(mensaje.getUsuarioReceptor());

			mensajeUpdated = mensajeService.save(mensajeActual);

		} catch (DataAccessException e) {
			response.put("mensajee", "Error al actualizar el mensaje en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensajee", "El mensaje ha sido actualizado con éxito!");
		response.put("mensaje", mensajeUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/mensajes/common/{idEmisor}/{idReceptor}")
	public List<Mensaje> getMensajesInCommon(@PathVariable Long idEmisor,@PathVariable Long idReceptor) {
		return mensajeService.getMensajesInCommon(idEmisor, idReceptor);
	}
	
	@GetMapping("/mensajes/usuario/{id}")
	public List<Usuario> getUsuarioMensajesInCommon(@PathVariable Long id) {
		List<Mensaje> listaMensajes = mensajeService.getUsuarioMensajesInCommon(id);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		for (int i = 0; i < listaMensajes.size(); i++) {
			if (listaMensajes.get(i).getUsuarioEmisor().getId() != id) {
				listaUsuarios.add(listaMensajes.get(i).getUsuarioEmisor());
			} else {
				if (listaMensajes.get(i).getUsuarioReceptor().getId() != id) {
					listaUsuarios.add(listaMensajes.get(i).getUsuarioReceptor());
				}
			}
		}
		return listaUsuarios.stream().distinct().collect(Collectors.toList());
	}
	

}
