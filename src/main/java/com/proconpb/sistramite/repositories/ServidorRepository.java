package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proconpb.sistramite.domain.Servidor;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Integer>{
	
}
