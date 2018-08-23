package com.proconpb.sistramite.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/setor")
public class SetorResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "REST está funcionando!";
	}
}
