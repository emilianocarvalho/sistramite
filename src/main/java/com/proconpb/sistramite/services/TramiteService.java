package com.proconpb.sistramite.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.repositories.TramiteRepository;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class TramiteService {
	
	@Autowired
	private TramiteRepository repo;
	
	public Tramite find(Integer id) {
		Optional<Tramite> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Tramite não encontrado! Id: " + id + ", Tipo: " + Setor.class.getName()));
	}
}
