package com.proconpb.sistramite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proconpb.sistramite.domain.Tramite;
import com.proconpb.sistramite.repositories.TramiteRepository;
import com.proconpb.sistramite.services.exceptions.DataIntegrityException;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class TramiteService {

	
	@Autowired
	private TramiteRepository repo;
	
	
	public Tramite find(Integer id) {
		Optional<Tramite> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Tramite não encontrado! Id: " + id + ", Tipo: " + Tramite.class.getName()));
	}
	
	@Transactional
	public Tramite insert(Tramite obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Tramite update(Tramite obj) {
		Tramite newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}
	
	public List<Tramite> findAll(){
		return repo.findAll();
	}
	
	public Page<Tramite> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	
	private void updateData(Tramite newObj, Tramite obj) {
		newObj.setDataMovimentacao(obj.getDataMovimentacao());
		newObj.setSetorDestino(obj.getSetorDestino());
		newObj.setAuto(obj.getAuto());
	}
}
