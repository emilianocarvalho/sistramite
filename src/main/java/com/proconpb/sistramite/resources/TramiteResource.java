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

import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.services.TramiteService;

@RestController
@RequestMapping(value="/tramites")
public class TramiteResource {

	@Autowired
	private TramiteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Tramite> find(@PathVariable Integer id) {		
		Tramite obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Tramite obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Tramite>> findAll() {
		List<Tramite> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
