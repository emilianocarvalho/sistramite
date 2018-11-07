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

import com.proconpb.sistramite.domain.Usuario;
import com.proconpb.sistramite.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {		
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	@RequestMapping(value="/{login}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable String login) {
		Usuario obj = service.findByLogin(login);
		return ResponseEntity.ok().body(obj);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Usuario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
