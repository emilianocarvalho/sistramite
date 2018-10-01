package com.proconpb.sistramite.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Auto;
import com.proconpb.sistramite.domain.PessoaFisica;
import com.proconpb.sistramite.domain.PessoaJuridica;
import com.proconpb.sistramite.repositories.AutoRepository;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class AutoService {
	
	@Autowired
	private AutoRepository repo;
		
	public Auto find(Integer id) {
		Optional<Auto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Auto não encontrado! Id: " + id + ", Tipo: " + Auto.class.getName()));
	}
	
	public Auto insert(Auto obj) {
		obj.setId(null);		
		if (obj.getEmpresa() instanceof PessoaJuridica){
			PessoaJuridica empresa = (PessoaJuridica) obj.getEmpresa();
			obj.setEmpresa(empresa);
		}		
		if (obj.getFiscal() instanceof PessoaFisica){
			PessoaFisica fiscal = (PessoaFisica) obj.getFiscal();
			obj.setFiscal(fiscal);
		}		
		return repo.save(obj);
	}
	
}
