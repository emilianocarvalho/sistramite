package com.proconpb.sistramite.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proconpb.sistramite.domain.Auto;
import com.proconpb.sistramite.domain.Fornecedor;
import com.proconpb.sistramite.domain.Servidor;
import com.proconpb.sistramite.repositories.AutoRepository;
import com.proconpb.sistramite.repositories.FornecedorRepository;
import com.proconpb.sistramite.repositories.ServidorRepository;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class AutoService {
	
	@Autowired
	private AutoRepository repo;
		
	@Autowired
	private ServidorRepository servidorRepo;
	
	@Autowired
	private FornecedorRepository fornecedorRepo;
	
	
	public Auto find(Integer id) {
		Optional<Auto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Auto não encontrado! Id: " + id + ", Tipo: " + Auto.class.getName()));
	}
	
	@Transactional
	public Auto insert(Auto obj) {
		obj.setId(null);		
		if (obj.getEmpresa() instanceof Fornecedor){
			Fornecedor empresa = (Fornecedor) obj.getEmpresa();
			fornecedorRepo.save(empresa);
		}		
		if (obj.getFiscal() instanceof Servidor){
			Servidor fiscal = (Servidor) obj.getFiscal();
			servidorRepo.save(fiscal);
		}		
		return repo.save(obj);
	}
	
	public List<Auto> findAll(){
		return repo.findAll();
	}
	
}
