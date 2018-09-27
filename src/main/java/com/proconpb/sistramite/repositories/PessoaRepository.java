package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proconpb.sistramite.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
	@Transactional(readOnly=true)
	Pessoa findByEmail(String email);
}
