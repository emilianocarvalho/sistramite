package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proconpb.sistramite.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer>{
	
}
