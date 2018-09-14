package com.proconpb.sistramite.domain;

import javax.persistence.Entity;

@Entity
public class PessoaJuridica extends Pessoa {
	private static final long serialVersionUID = 1L;

	private String cnpj;
	private String razaoSocial;
	
	public PessoaJuridica() {
	}
	
	public PessoaJuridica(Integer id, String nome, String email, String cnpj, String razaoSocial) {
		super(id, nome, email);
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	
}
