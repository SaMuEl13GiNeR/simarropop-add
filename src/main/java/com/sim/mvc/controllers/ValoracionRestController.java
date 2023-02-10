package com.sim.mvc.controllers;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sim.mvc.models.entity.Categoria;
import com.sim.mvc.models.entity.Usuario;
import com.sim.mvc.models.entity.Valoracion;
import com.sim.mvc.models.services.IValoracionService;

@CrossOrigin(origins = { "http://192.168.8.226:4200" })
@RestController
@RequestMapping("/simarropop")
public class ValoracionRestController {
	

	@Autowired
	@Qualifier("ValoracionServiceImpl")
	private IValoracionService valoracionService;
	
	@GetMapping("/valoraciones")
	public List<Valoracion> findAll() {
		return valoracionService.findAll();
	}
	
	@GetMapping("/valoraciones/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Valoracion valoracion = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			valoracion = valoracionService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(valoracion == null) {
			response.put("mensaje", "La valoracion ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Valoracion>(valoracion, HttpStatus.OK);
	}
	
	@PostMapping("/valoraciones/nuevo")
	public ResponseEntity<?> create(@RequestBody Valoracion valoracion, BindingResult result) {
		
		Valoracion valoracionNew = null;
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
			valoracionNew = valoracionService.save(valoracion);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La valoracion ha sido creado con éxito!");
		response.put("valoracion", valoracionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/valoraciones/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			valoracionService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la valoracion de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Valoracion eliminada con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/valoraciones/media/{id}")
	public float findByNombre(@PathVariable Long id) {		
		return valoracionService.mediaUsuario(id);
	}
}
