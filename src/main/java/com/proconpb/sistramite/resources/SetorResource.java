package com.proconpb.sistramite.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.services.SetorService;

@RestController
@RequestMapping(value="/setor")
public class SetorResource {
	
	@Autowired
	private SetorService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Setor obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
