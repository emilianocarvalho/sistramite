package com.proconpb.sistramite.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.services.TramiteService;

@RestController
@RequestMapping(value="/tramite")
public class TramiteResource {
	
	@Autowired
	private TramiteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Tramite> find(@PathVariable Integer id) {
		
		Tramite obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
