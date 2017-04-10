package com.hortifruti.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "PERFIL")
public class Perfil {
	
	@Id
	@GeneratedValue
	@Column(name="id_perfil")
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public List<Pessoa> getUsuario() {
		return pessoa;
	}
	public void setUsuario(List<Pessoa> pessoa) {
		this.pessoa = pessoa;
	}
	@Column(name="nome", nullable=false)
	private String nome;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="perfil")
	private List<Pessoa> pessoa;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
