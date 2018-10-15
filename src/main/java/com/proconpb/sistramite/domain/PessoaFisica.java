package com.proconpb.sistramite.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pessoaFisica")
public class PessoaFisica extends Fornecedor {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@CPF
	private String cpf;

	public PessoaFisica() {
	}
	
	public PessoaFisica(Integer id, String razaoSocial, String nome, String email, String cpf) {
		super(id, razaoSocial, nome, email);
		this.cpf = cpf;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	
	
}
