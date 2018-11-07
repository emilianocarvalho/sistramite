package com.proconpb.sistramite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proconpb.sistramite.domain.Usuario;
import com.proconpb.sistramite.repositories.UsuarioRepository;
import com.proconpb.sistramite.services.exceptions.DataIntegrityException;
import com.proconpb.sistramite.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public Usuario findByLogin(String login) {
		
		//UserSS user = UserService.autenticated();
		//if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
		//	throw new AuthorizationException("Acesso negado");
		//}
		Usuario obj = repo.findByLogin(login);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não econtrado! Id: , Tipo: ");
		}
		return obj;
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setLogin(obj.getLogin());
		newObj.setSenha(obj.getSenha());
	}
}
