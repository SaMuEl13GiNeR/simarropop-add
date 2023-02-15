package com.sim.mvc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


import com.sim.mvc.models.entity.Foto;

@CrossOrigin(origins = { "http://192.168.8.226:4200" })
@RestController
@RequestMapping("/simarropop")
public class FotoRestController {
	


	@GetMapping("/fotos/{entity}/{id}")
	public ResponseEntity<?> findById(@PathVariable String entity, @PathVariable Long id) {
		
//		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost("http://192.168.8.226:8069/jsonrpc");
		
		Foto foto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			foto = new Foto();
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		if(foto == null) {
//			response.put("mensaje", "El articulo ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
		
		return new ResponseEntity<Foto>(foto, HttpStatus.OK);
	}
	
}
