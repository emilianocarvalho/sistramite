package com.proconpb.sistramite.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proconpb.sistramite.domain.Servidor;
import com.proconpb.sistramite.services.ServidorService;

@RestController
@RequestMapping(value="/servidores")
public class ServidorResource {

	@Autowired
	private ServidorService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Servidor> find(@PathVariable Integer id) {		
		Servidor obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Servidor obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Servidor>> findAll() {
		List<Servidor> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
