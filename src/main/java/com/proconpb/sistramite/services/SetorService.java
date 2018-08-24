package com.proconpb.sistramite.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.repositories.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository repo;
	
	public Setor find(Integer id) {
		Optional<Setor> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
