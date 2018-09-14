package com.proconpb.sistramite.domain;

import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	
	public Usuario() {
	}
	
	public Usuario(Integer id, String nome, String email, String login, String senha) {
		super(id, nome, email);
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
