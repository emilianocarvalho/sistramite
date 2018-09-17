package com.proconpb.sistramite.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.repositories.SetorRepository;
import com.proconpb.sistramite.services.exceptions.DataIntegrityException;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository repo;
	
	public Setor find(Integer id) {
		Optional<Setor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Setor não encontrado! Id: " + id + ", Tipo: " + Setor.class.getName()));
	}
	
	public Setor insert(Setor obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Setor update(Setor obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um setor que possui trâmites.");
		}
	}
	
	public List<Setor> findAll(){
		return repo.findAll();
	}
}
