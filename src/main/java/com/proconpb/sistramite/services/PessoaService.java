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

import com.proconpb.sistramite.domain.Cidade;
import com.proconpb.sistramite.domain.Endereco;
import com.proconpb.sistramite.domain.Pessoa;
import com.proconpb.sistramite.dto.PessoaDTO;
import com.proconpb.sistramite.dto.PessoaNewDTO;
import com.proconpb.sistramite.repositories.EnderecoRepository;
import com.proconpb.sistramite.repositories.PessoaRepository;
import com.proconpb.sistramite.services.exceptions.DataIntegrityException;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Pessoa find(Integer id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
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
	
	public List<Pessoa> findAll(){
		return repo.findAll();
	}
	
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getEmail());
	}
	
	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Pessoa p = new Pessoa(null, objDto.getNome(), objDto.getEmail());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), p, cid);
		p.getEnderecos().add(end);
		p.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			p.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			p.getTelefones().add(objDto.getTelefone3());
		}
		return p;
	}
	
	private void updateData(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
