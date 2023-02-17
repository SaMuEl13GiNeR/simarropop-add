package com.sim.mvc.controllers;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
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

import com.sim.mvc.models.entity.Articulo;
import com.sim.mvc.models.entity.Articulo2;
import com.sim.mvc.models.services.IArticuloService;

@CrossOrigin(origins = { "http://192.168.8.226:4200" })
@RestController
@RequestMapping("/simarropop")
public class ArticuloRestController {

	@Autowired
	@Qualifier("ArticuloServiceImpl")
	private IArticuloService articuloService;
	

	@GetMapping("/articulos")
	public List<Articulo2> findAll() {
		return convertListFromArticuloToArticulo2(articuloService.findAll());
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
		
		return new ResponseEntity<Articulo2>(convertFromArticuloToArticulo2(articulo), HttpStatus.OK);
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
		return new ResponseEntity<Articulo2>(convertFromArticuloToArticulo2(articuloNew), HttpStatus.CREATED);
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
//			
			articuloActual.setTitulo(articulo.getTitulo());
			articuloActual.setLikes(articulo.getLikes());
			articuloActual.setDescripcion(articulo.getDescripcion());
			articuloActual.setPrecio(articulo.getPrecio());
			articuloActual.setEstado(articulo.getEstado());
			articuloActual.setUsuarioComprador(articulo.getUsuarioComprador());
			articuloActual.setUsuarioVendedor(articulo.getUsuarioVendedor());
			articuloActual.setVendido(articulo.getVendido());
			articuloActual.setCategoria(articulo.getCategoria());
//			articuloActual.setFoto(articulo.getFoto());


			articuloUpdated = articuloService.save(articuloActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el articulo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El articulo ha sido actualizado con éxito!");
		response.put("articulo", articuloUpdated);

		return new ResponseEntity<Articulo2>(convertFromArticuloToArticulo2(articuloUpdated), HttpStatus.CREATED);
	}
	
	@GetMapping("/articulos/usuarioVendedor/{id}")
	public List<Articulo2> findByUsuarioId(@PathVariable Long id){
		return convertListFromArticuloToArticulo2(articuloService.findByUsuarioVendedorId(id));
	}

	@GetMapping("/articulos/categoria/{id}")
	public List<Articulo2> findByCategoriaId(@PathVariable Long id){
		return convertListFromArticuloToArticulo2(articuloService.findByCategoriaId(id));
	}
	
	@GetMapping("/articulos/precio/lower")
	public List<Articulo2> findAllOrderByLowerPrecio(){
		return convertListFromArticuloToArticulo2(articuloService.findAllOrderByLowerPrecio());
	}
	
	@GetMapping("/articulos/precio/higher")
	public List<Articulo2> findAllOrderByHigherPrecio(){
		return convertListFromArticuloToArticulo2(articuloService.findAllOrderByHigherPrecio());
	}
	
	@GetMapping("/articulos/titulo/")
	public List<Articulo2> findByTitulo2() {
			return convertListFromArticuloToArticulo2(articuloService.findAll());
	}
	
	@GetMapping("/articulos/titulo/{titulo}")
	public List<Articulo2> findByTitulo(@PathVariable String titulo) {
		return convertListFromArticuloToArticulo2(articuloService.findByTitulo(titulo));
	}
	
	@GetMapping("/articulos/ajenos/{id}/titulo/{titulo}")
	public List<Articulo2> findByTitulo(@PathVariable Long id, @PathVariable String titulo) {
		return convertListFromArticuloToArticulo2(articuloService.findByAjenoTitulo(id, titulo));
	}
	
	@GetMapping("/articulos/ajenos/{id}")
	public List<Articulo2> findAllAlien(@PathVariable Long id) {
		return convertListFromArticuloToArticulo2(articuloService.findAllAlien(id));
	}
	
	@GetMapping("/articulos/noVendidos/ajenos/{id}")
	public List<Articulo2> findAllAlienNoVendidos(@PathVariable Long id) {
		return convertListFromArticuloToArticulo2(articuloService.findAllAlienNoVendidos(id));
	}
	
	@GetMapping("/articulos/vendidos/usuario/{id}")
	public List<Articulo2> findAllVendidos(@PathVariable Long id) {
		return convertListFromArticuloToArticulo2(articuloService.findAllVendidos(id));
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
	
	public Articulo2 getArticuloFoto(Articulo articulo) {
		
		Articulo2 articulo2 = new Articulo2(articulo);
		
		try {
			
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost("http://192.168.8.226:8069/jsonrpc");
		
		Map<String, Object> response2 = new HashMap<>();		
			
			JSONObject jsonRpcRequest = new JSONObject();
			jsonRpcRequest.put("jsonrpc", "2.0");
			jsonRpcRequest.put("id", 1);
			jsonRpcRequest.put("method", "call");
			JSONObject params = new JSONObject();
			params.put("service", "object");
			params.put("method", "execute");
			JSONArray args = new JSONArray();
			args.put("sge22");
			args.put(2);
			args.put("1234");
			args.put("simarropop.articulo");
			args.put("read");
			args.put(articulo.getId());
			JSONArray campo = new JSONArray();
			campo.put("foto");
			args.put(campo);
			params.put("args", args);
			jsonRpcRequest.put("params", params);
			
			
			
			
			StringEntity input = new StringEntity(jsonRpcRequest.toString());
			input.setContentType("application/json");
			postRequest.setEntity(input);
			
			
	
			HttpResponse response3 = httpClient.execute(postRequest);
			String jsonResponse = EntityUtils.toString(response3.getEntity());
			JSONObject jsonRpcResponse = new JSONObject(jsonResponse);
			
			String total = ((JSONObject) jsonRpcResponse.getJSONArray("result").get(0)).getString("foto");
			System.out.println(total);

			
			if(!total.equals("")) {
				articulo2.setFoto(total);
			}

		} catch(Exception e) {
			e.getMessage();
		}
			
			return articulo2;
	}
	
	public List<Articulo2> convertListFromArticuloToArticulo2(List<Articulo> articulos){
		List<Articulo2> articulos2 = new ArrayList();
		for( int i = 0; i< articulos.size(); i++) {
			articulos2.add(getArticuloFoto(articulos.get(i)));
		}
		return articulos2;
	}
	
	public Articulo2 convertFromArticuloToArticulo2(Articulo articulo){
		return getArticuloFoto(articulo);
	}
	
}
