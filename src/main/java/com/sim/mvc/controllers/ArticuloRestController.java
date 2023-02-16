package com.sim.mvc.controllers;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
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

import com.sim.mvc.models.entity.Articulo;
import com.sim.mvc.models.entity.Articulo2;
import com.sim.mvc.models.services.IArticuloService;
import com.sim.mvc.models.services.IArticuloService2;

@CrossOrigin(origins = { "http://192.168.8.226:4200" })
@RestController
@RequestMapping("/simarropop")
public class ArticuloRestController {

	@Autowired
	@Qualifier("ArticuloServiceImpl")
	private IArticuloService articuloService;
	
	@Autowired
	@Qualifier("ArticuloServiceImpl2")
	private IArticuloService2 articuloService2;

	@GetMapping("/articulos")
	public List<Articulo> findAll() {
		return articuloService.findAll();
	}
	
	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Articulo articulo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			articulo = articuloService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(articulo == null) {
			response.put("mensaje", "El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}
	
	@PostMapping("/articulos/nuevo")
	public ResponseEntity<?> create(@RequestBody Articulo articulo, BindingResult result) {
//		articulo.setPartnerID(articulo.getUsuarioVendedor().getId().intValue());
//		articulo.setName(articulo.getTitulo());
//		articulo.setDateOrder(new Timestamp(System.currentTimeMillis()));
//		articulo.setPartner_invoice_id(82);
//		articulo.setPartner_shipping_id(82);
//		articulo.setPricelist_id(1);
//		articulo.setCompany_id(1);
		
		Articulo articuloNew = null;
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
			System.out.println(articulo.toString());
			articuloNew = articuloService.save(articulo);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El articulo ha sido creado con éxito!");
		response.put("articulo", articuloNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/articulos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			articuloService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el articulo de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Articulo eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("/articulos/{id}")
	public ResponseEntity<?> update(@RequestBody Articulo articulo, BindingResult result, @PathVariable Long id) {

		Articulo articuloActual = articuloService.findById(id);

		Articulo articuloUpdated = null;

		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (articuloActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el articulo ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
//			if( articulo.getTitulo() != null) {
//				articuloActual.setTitulo(articulo.getTitulo());
//			}
//			if( articulo.getLikes() != 0) {
//				articuloActual.setLikes(articulo.getLikes());
//			}
//			if( articulo.getDescripcion() != null) {
//				articuloActual.setDescripcion(articulo.getDescripcion());
//			}
//			if( articulo.getPrecio() != 0) {
//				articuloActual.setPrecio(articulo.getPrecio());
//			}
//			if( articulo.getEstado() != null) {
//				articuloActual.setEstado(articulo.getEstado());
//			}
//			if( articulo.getUsuarioComprador() != null) {
//				articuloActual.setUsuarioComprador(articulo.getUsuarioComprador());
//			}
//			if( articulo.getUsuarioVendedor() != null) {
//				articuloActual.setUsuarioVendedor(articulo.getUsuarioVendedor());
//			}
//			if( articulo.getVendido() != false) {
//				articuloActual.setVendido(articulo.getVendido());
//			}
//			if( articulo.getCategoria() != null) {
//				articuloActual.setCategoria(articulo.getCategoria());
//			}
//			if( articulo.getFotos() != null) {
//				articuloActual.setFotos(articulo.getFotos());
//			}
			articuloActual.setTitulo(articulo.getTitulo());
			articuloActual.setLikes(articulo.getLikes());
			articuloActual.setDescripcion(articulo.getDescripcion());
			articuloActual.setPrecio(articulo.getPrecio());
			articuloActual.setEstado(articulo.getEstado());
			articuloActual.setUsuarioComprador(articulo.getUsuarioComprador());
			articuloActual.setUsuarioVendedor(articulo.getUsuarioVendedor());
			articuloActual.setVendido(articulo.getVendido());
			articuloActual.setCategoria(articulo.getCategoria());
			articuloActual.setFotos(articulo.getFotos());


			articuloUpdated = articuloService.save(articuloActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el articulo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El articulo ha sido actualizado con éxito!");
		response.put("articulo", articuloUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/articulos/usuarioVendedor/{id}")
	public List<Articulo> findByUsuarioId(@PathVariable Long id){
		return articuloService.findByUsuarioVendedorId(id);
	}

	@GetMapping("/articulos/categoria/{id}")
	public List<Articulo> findByCategoriaId(@PathVariable Long id){
		return articuloService.findByCategoriaId(id);
	}
	
	@GetMapping("/articulos/precio/lower")
	public List<Articulo> findAllOrderByLowerPrecio(){
		return articuloService.findAllOrderByLowerPrecio();
	}
	
	@GetMapping("/articulos/precio/higher")
	public List<Articulo> findAllOrderByHigherPrecio(){
		return articuloService.findAllOrderByHigherPrecio();
	}
	
	@GetMapping("/articulos/titulo/")
	public List<Articulo> findByTitulo2() {
			return articuloService.findAll();
	}
	
	@GetMapping("/articulos/titulo/{titulo}")
	public List<Articulo> findByTitulo(@PathVariable String titulo) {
		return articuloService.findByTitulo(titulo);
	}
	
	@GetMapping("/articulos/ajenos/{id}/titulo/{titulo}")
	public List<Articulo> findByTitulo(@PathVariable Long id, @PathVariable String titulo) {
		return articuloService.findByAjenoTitulo(id, titulo);
	}
	
	@GetMapping("/articulos/ajenos/{id}")
	public List<Articulo> findAllAlien(@PathVariable Long id) {
		return articuloService.findAllAlien(id);
	}
	
	@GetMapping("/articulos/noVendidos/ajenos/{id}")
	public List<Articulo> findAllAlienNoVendidos(@PathVariable Long id) {
		return articuloService.findAllAlienNoVendidos(id);
	}
	
	@GetMapping("/articulos/vendidos/usuario/{id}")
	public List<Articulo> findAllVendidos(@PathVariable Long id) {
		return articuloService.findAllVendidos(id);
	}
	
	@GetMapping("/articulos/like/{id}/{isLiked}")
	public ResponseEntity<?> like(@PathVariable Long id, @PathVariable boolean isLiked) {
		Articulo articuloActual = articuloService.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		if (articuloActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el articulo ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			if (articuloActual.getLikes() == 0 && !isLiked) {
				response.put("mensaje", "No puedes quitar un like a un aarticulo sin likes!");
			} else {
				if(isLiked) {
					articuloActual.setLikes(articuloActual.getLikes() + 1);
					response.put("mensaje", "Like!");
				} else {
					articuloActual.setLikes(articuloActual.getLikes() - 1);
					response.put("mensaje", "DisLike!");
				}
				articuloService.save(articuloActual);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el articulo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("likes", articuloActual.getLikes());
		response.put("articulo", articuloActual.getId());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
