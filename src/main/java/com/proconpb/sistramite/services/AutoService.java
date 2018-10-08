package com.proconpb.sistramite.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proconpb.sistramite.domain.Auto;
import com.proconpb.sistramite.domain.PessoaFisica;
import com.proconpb.sistramite.domain.PessoaJuridica;
import com.proconpb.sistramite.repositories.AutoRepository;
import com.proconpb.sistramite.repositories.PessoaFisicaRepository;
import com.proconpb.sistramite.repositories.PessoaJuridicaRepository;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class AutoService {
	
	@Autowired
	private AutoRepository repo;
		
	@Autowired
	private PessoaFisicaRepository pFRepo;
	
	@Autowired
	private PessoaJuridicaRepository pJRepo;
	
	public Auto find(Integer id) {
		Optional<Auto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Auto não encontrado! Id: " + id + ", Tipo: " + Auto.class.getName()));
	}
	
	@Transactional
	public Auto insert(Auto obj) {
		obj.setId(null);		
		if (obj.getEmpresa() instanceof PessoaJuridica){
			PessoaJuridica empresa = (PessoaJuridica) obj.getEmpresa();
			pJRepo.save(empresa);
		}		
		if (obj.getFiscal() instanceof PessoaFisica){
			PessoaFisica fiscal = (PessoaFisica) obj.getFiscal();
			pFRepo.save(fiscal);
		}		
		return repo.save(obj);
	}
	
	public List<Auto> findAll(){
		return repo.findAll();
	}
	
}
