package com.proconpb.sistramite.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Setor;
import com.proconpb.sistramite.dto.SetorDTO;
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
		Setor newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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
	
	public Page<Setor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Setor fromDTO(SetorDTO objDto) {
		return new Setor(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Setor newObj, Setor obj) {
		newObj.setNome(obj.getNome());
	}
}
