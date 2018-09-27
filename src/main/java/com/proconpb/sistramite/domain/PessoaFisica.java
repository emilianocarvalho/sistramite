package com.proconpb.sistramite.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class PessoaFisica extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF(message="CPF inválido")
	private String cpf;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String matricula;
	
	@NotEmpty(message="Preenchimento obrigatório")
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
