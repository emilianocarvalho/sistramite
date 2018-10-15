package com.proconpb.sistramite.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pessoaJuridica")
public class PessoaJuridica extends Fornecedor {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório")
	@CNPJ
	private String cnpj;
	
	public PessoaJuridica() {
	}
	
	public PessoaJuridica(Integer id, String razaoSocial, String nomeFantasia, String email, String cnpj) {
		super(id, razaoSocial, nomeFantasia, email);
		this.cnpj = cnpj;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}