package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proconpb.sistramite.domain.Tramite;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Integer>{
	
}
