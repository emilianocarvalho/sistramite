package com.proconpb.sistramite.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Pessoa;
import com.proconpb.sistramite.repositories.PessoaRepository;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repo;
	
	public Pessoa find(Integer id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}
}
