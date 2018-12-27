package com.proconpb.sistramite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proconpb.sistramite.domain.Usuario;
import com.proconpb.sistramite.repositories.UsuarioRepository;
import com.proconpb.sistramite.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usu = repo.findByLogin(email);
		if (usu  == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(usu.getId(), usu.getLogin(), usu.getSenha(), usu.getPerfis());
	}

	
}
