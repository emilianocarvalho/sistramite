package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proconpb.sistramite.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
}
