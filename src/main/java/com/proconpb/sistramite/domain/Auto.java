package com.proconpb.sistramite.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Auto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name="autuado_id")
	private Fornecedor autuado;
	
	private String tipoDeInfracao;
	private String tipoDeReclamacao;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date dataLavratura;
	
	@ManyToOne
	@JoinColumn(name="fiscal_id")
	private Servidor fiscal;
	
	@JsonIgnore
	@OneToMany(mappedBy="auto", cascade=CascadeType.ALL)
	private List<Tramite> tramites = new ArrayList<>();
	
	public Auto() {
		
	}

	public Auto(Integer id, Integer numero, Fornecedor autuado, String tipoDeInfracao, String tipoDeReclamacao,
			Date dataLavratura, Servidor fiscal) {
		super();
		this.id = id;
		this.numero = numero;
		this.autuado = autuado;
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

	public Fornecedor getAutuado() {
		return autuado;
	}

	public void setAutuado(Fornecedor autuado) {
		this.autuado = autuado;
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

	public Servidor getFiscal() {
		return fiscal;
	}

	public void setFiscal(Servidor fiscal) {
		this.fiscal = fiscal;
	}

	@JsonIgnore
	public List<Tramite> getTramites() {
		return tramites;
	}

	public void setTramites(List<Tramite> tramites) {
		this.tramites = tramites;
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
