package com.hortifruti.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "SUB_CATEGORIA")
public class SubCategoria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9003458528260045191L;

	@Id
	@GeneratedValue
	@Column(name = "id_categoria")
	private Long id;
	
	@Column(name = "NAME", nullable = false, length = 50) 
	@Length(max = 50)
	@NotNull(message="Informe um nome para a categoria.")
	private String nome;
	
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
