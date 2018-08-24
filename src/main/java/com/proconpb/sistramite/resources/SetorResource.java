package com.proconpb.sistramite.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proconpb.sistramite.domain.Setor;

@RestController
@RequestMapping(value="/setor")
public class SetorResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Setor> listar() {
		
		Setor s1 = new Setor(1, "TI", 1);
		Setor s2 = new Setor(2, "RH", 2);
		
		List<Setor> lista = new ArrayList<>();
		lista.add(s1);
		lista.add(s2);
		
		return lista;
	}
}
