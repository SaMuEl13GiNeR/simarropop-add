package com.sim.mvc.controllers;

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

import com.sim.mvc.models.entity.Usuario;
import com.sim.mvc.models.entity.Usuario2;
import com.sim.mvc.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://192.168.8.226:4200" })
@RestController
@RequestMapping("/simarropop")
public class UsuarioRestController {
	

	@Autowired
	@Qualifier("UsuarioServiceImpl")
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario2> findAll() {
		return convertListFromUsuarioToUsuario2(usuarioService.findAll());
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario2>(convertFromUsuarioToUsuario2(usuario), HttpStatus.OK);
	}
	
	@PostMapping("/usuarios/nuevo")
	public ResponseEntity<?> create(@RequestBody Usuario2 usuario, BindingResult result) {
		Usuario2 usuarioNew2 = null;
		Usuario usuarioNew = new Usuario(usuario);
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			return null;
		}
		try {
			usuarioNew2 = getUsuarioAvatar(usuarioService.save(usuarioNew));
			usuarioNew2.setAvatar(usuario.getAvatar());
			updateUsuarioAvatar(usuarioNew2);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		}
		
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", usuarioNew2);
		return new ResponseEntity<Usuario2>(usuarioNew2, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Usuario eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario2 usuario, BindingResult result, @PathVariable Long id) {
		Usuario usuarioActual = usuarioService.findById(id);
		Usuario2 usuarioActual2 = convertFromUsuarioToUsuario2(usuarioActual);
		Usuario2 usuarioUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			return null;
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			return null;
		}

		try {

			usuarioActual2.setNombre(usuario.getNombre());
			usuarioActual2.setApellidos(usuario.getApellidos());
			usuarioActual2.setUbicacion(usuario.getUbicacion());
			usuarioActual2.setCorreo(usuario.getCorreo());
			usuarioActual2.setContrasenya(usuario.getContrasenya());
			usuarioActual2.setAvatar(usuario.getAvatar());
			usuarioActual2.setUser(usuario.getUser());

			Usuario u = new Usuario(usuarioActual2);
			usuarioUpdated = convertFromUsuarioToUsuario2(usuarioService.save(u));
			usuarioUpdated.setAvatar(usuario.getAvatar());
			updateUsuarioAvatar(usuarioUpdated);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			return null;
		}

		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario", usuarioUpdated);

		return new ResponseEntity<Usuario2>(usuarioUpdated, HttpStatus.OK);
	}
	
	@GetMapping("/usuarios/nombre/{nombre}")
	public List<Usuario2> findByNombre(@PathVariable String nombre) {
		return convertListFromUsuarioToUsuario2(usuarioService.findByNombre(nombre));
	}
	
	@GetMapping("/usuarios/validar/{correo}/{contrasenya}")
	public Usuario2 findByNombre(@PathVariable String correo, @PathVariable String contrasenya) {
		Usuario usuario = new Usuario();
		usuario.setCorreo(correo);
		usuario.setContrasenya(contrasenya);
		return convertFromUsuarioToUsuario2(usuarioService.validar(usuario));
	}
	
	
	public Usuario2 getUsuarioAvatar(Usuario usuario) {
		
		Usuario2 usuario2 = new Usuario2(usuario);
		
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
			args.put("res.partner");
			args.put("read");
			args.put(usuario.getId());
			JSONArray campo = new JSONArray();
			campo.put("avatar");
			args.put(campo);
			params.put("args", args);
			jsonRpcRequest.put("params", params);
			
			
			
			
			StringEntity input = new StringEntity(jsonRpcRequest.toString());
			input.setContentType("application/json");
			postRequest.setEntity(input);
			
			
	
			HttpResponse response3 = httpClient.execute(postRequest);
			String jsonResponse = EntityUtils.toString(response3.getEntity());
			JSONObject jsonRpcResponse = new JSONObject(jsonResponse);
			
			String total = ((JSONObject) jsonRpcResponse.getJSONArray("result").get(0)).getString("avatar");
			System.out.println(total);

			
			if(!total.equals("")) {
				usuario2.setAvatar(total);
			}

		} catch(Exception e) {
			e.getMessage();
		}
			
			return usuario2;
	}
	
	public void updateUsuarioAvatar(Usuario2 usuario) {
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
			args.put("res.partner");
			args.put("write");
			args.put(usuario.getId());
			JSONObject objecte = new JSONObject();
			objecte.put("avatar", usuario.getAvatar());
			
			args.put(objecte);
			params.put("args", args);
			jsonRpcRequest.put("params", params);
				
			StringEntity input = new StringEntity(jsonRpcRequest.toString());
			input.setContentType("application/json");
			postRequest.setEntity(input);
			
			HttpResponse response3 = httpClient.execute(postRequest);
			String jsonResponse = EntityUtils.toString(response3.getEntity());
			JSONObject jsonRpcResponse = new JSONObject(jsonResponse);
			
		} catch(Exception e) {
			e.getMessage();
		}	
	}
	
	
	public List<Usuario2> convertListFromUsuarioToUsuario2(List<Usuario> usuarios){
		List<Usuario2> usuarios2 = new ArrayList();
		for( int i = 0; i< usuarios.size(); i++) {
			usuarios2.add(getUsuarioAvatar(usuarios.get(i)));
		}
		return usuarios2;
	}
	
	public Usuario2 convertFromUsuarioToUsuario2(Usuario usuario){
		return getUsuarioAvatar(usuario);
	}

}
