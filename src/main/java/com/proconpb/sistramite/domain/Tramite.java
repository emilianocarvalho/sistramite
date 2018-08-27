package com.proconpb.sistramite.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tramite implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
//	private Date data;
	//private Auto auto;
	//private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setorDestino;
	
	public Tramite() {
		
	}
	public Tramite(Integer id, Setor setor) {
		super();
		this.id = id;
		this.setorDestino = setor;
	//	this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Setor getSetorDestino() {
		return setorDestino;
	}
	public void setSetorDestino(Setor setorDestino) {
		this.setorDestino = setorDestino;
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
		Tramite other = (Tramite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
