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

import com.proconpb.sistramite.domain.Servidor;
import com.proconpb.sistramite.repositories.ServidorRepository;
import com.proconpb.sistramite.services.exceptions.DataIntegrityException;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class ServidorService {

	@Autowired
	private ServidorRepository repo;
	
	
	public Servidor find(Integer id) {
		Optional<Servidor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Servidor não encontrado! Id: " + id + ", Tipo: " + Servidor.class.getName()));
	}
	
	@Transactional
	public Servidor insert(Servidor obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Servidor update(Servidor obj) {
		Servidor newObj = find(obj.getId());
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
	
	public List<Servidor> findAll(){
		return repo.findAll();
	}
	
	public Page<Servidor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Servidor newObj, Servidor obj) {
		newObj.setNome(obj.getNome());
		newObj.setMatricula(obj.getMatricula());
		newObj.setFuncao(obj.getFuncao());
	}
	
}
