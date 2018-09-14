package com.proconpb.sistramite.domain;

import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String matricula;
	private String funcao;

	public PessoaFisica() {
	}
	
	public PessoaFisica(Integer id, String nome, String email, String cpf, String matricula, String funcao) {
		super(id, nome, email);
		this.cpf = cpf;
		this.matricula = matricula;
		this.funcao = funcao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
	
}
