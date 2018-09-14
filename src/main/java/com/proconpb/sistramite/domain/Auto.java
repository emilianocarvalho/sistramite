package com.proconpb.sistramite.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Auto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer numero;
	
	@OneToOne
	private Pessoa empresa;
	
	private String tipoDeInfracao;
	private String tipoDeReclamacao;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataLavratura;
	
	@ManyToOne
	@JoinColumn(name="fiscal_id")
	private Pessoa fiscal;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="auto")
	private Tramite tramite;
	
	public Auto() {
		
	}

	public Auto(Integer id, Integer numero, Pessoa empresa, String tipoDeInfracao, String tipoDeReclamacao,
			Date dataLavratura, Pessoa fiscal) {
		super();
		this.id = id;
		this.numero = numero;
		this.empresa = empresa;
		this.tipoDeInfracao = tipoDeInfracao;
		this.tipoDeReclamacao = tipoDeReclamacao;
		this.dataLavratura = dataLavratura;
		this.fiscal = fiscal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}

	public String getTipoDeInfracao() {
		return tipoDeInfracao;
	}

	public void setTipoDeInfracao(String tipoDeInfracao) {
		this.tipoDeInfracao = tipoDeInfracao;
	}

	public String getTipoDeReclamacao() {
		return tipoDeReclamacao;
	}

	public void setTipoDeReclamacao(String tipoDeReclamacao) {
		this.tipoDeReclamacao = tipoDeReclamacao;
	}

	public Date getDataLavratura() {
		return dataLavratura;
	}

	public void setDataLavratura(Date dataLavratura) {
		this.dataLavratura = dataLavratura;
	}

	public Pessoa getFiscal() {
		return fiscal;
	}

	public void setFiscal(Pessoa fiscal) {
		this.fiscal = fiscal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auto other = (Auto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
