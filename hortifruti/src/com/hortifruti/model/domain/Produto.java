package com.hortifruti.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8270652450451149590L;

	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Long id;
	
	@NotNull(message="Informe o nome do produto.")
	private String nome;
	private String descricao;
	
	@Lob
	@Column(name="imagem")
	private byte[] imagem;
	
	@Lob
	@Column( name = "imagem_pequena" )
	private byte[] imagemPequena;
	
	@NotNull(message="Informe um valor.")
	private Double valor;
	private Double valorAnterior;
	
	@Column(name = "quantidade", nullable = false, columnDefinition = "int default 0")
	@Size(min = 0, max = 9999)
	private int quantidade;

	@Column(name = "promocao", nullable = false, columnDefinition ="tinyint default true")
	private boolean promocao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sub_categoria", nullable=false)
	private SubCategoria subCategoria;

	
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(Double valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isPromocao() {
		return promocao;
	}

	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

}
