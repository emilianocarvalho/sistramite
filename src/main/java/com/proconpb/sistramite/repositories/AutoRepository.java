package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proconpb.sistramite.domain.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer>{
	
}
