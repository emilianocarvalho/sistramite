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
import com.proconpb.sistramite.domain.Fornecedor;
import com.proconpb.sistramite.dto.FornecedorDTO;
import com.proconpb.sistramite.dto.FornecedorNewDTO;
import com.proconpb.sistramite.repositories.EnderecoRepository;
import com.proconpb.sistramite.repositories.FornecedorRepository;
import com.proconpb.sistramite.services.exceptions.DataIntegrityException;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Fornecedor find(Integer id) {
		Optional<Fornecedor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Fornecedor não encontrada! Id: " + id + ", Tipo: " + Fornecedor.class.getName()));
	}
	
	@Transactional
	public Fornecedor insert(Fornecedor obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Fornecedor update(Fornecedor obj) {
		Fornecedor newObj = find(obj.getId());
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
	
	public List<Fornecedor> findAll(){
		return repo.findAll();
	}
	
	public Page<Fornecedor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Fornecedor fromDTO(FornecedorDTO objDto) {
		return new Fornecedor(objDto.getId(), objDto.getRazaoSocial(), objDto.getNomeFantasia(), objDto.getEmail());
	}
	
	public Fornecedor fromDTO(FornecedorNewDTO objDto) {
		Fornecedor p = new Fornecedor(null, objDto.getRazaoSocial(), objDto.getNomeFantasia(), objDto.getEmail());
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
	
	private void updateData(Fornecedor newObj, Fornecedor obj) {
		newObj.setRazaoSocial(obj.getRazaoSocial());
		newObj.setNomeFantasia(obj.getNomeFantasia());
		newObj.setEmail(obj.getEmail());
	}
}
