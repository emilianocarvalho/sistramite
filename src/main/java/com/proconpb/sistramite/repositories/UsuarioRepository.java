package com.proconpb.sistramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proconpb.sistramite.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly=true)
	Usuario findByLogin(String login);
	
}
