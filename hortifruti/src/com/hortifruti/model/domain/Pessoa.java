package com.hortifruti.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.hortifruti.enumeration.SexoEnum;
import com.hortifruti.enumeration.UfEnum;

@Entity
@Table(name = "PESSOA")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = -8210844505236907777L;

	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private Long id;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "enum('MASCULINO','FEMININO')")
    private SexoEnum sexo;
	
	@JoinColumn(unique=true)
	private String email;
	
	@NotNull(message="Digite o login.")
	@JoinColumn(unique=true)
	private String login;
	
	@NotNull(message="Digite a senha.")
	private String senha;
	
	@Column(name = "usuario_ativo", nullable = false, columnDefinition ="tinyint default true")
	private boolean usuarioAtivo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_perfil", nullable=false)
	private Perfil perfil;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private String cidade;
	
	private UfEnum uf;
	
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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isUsuarioAtivo() {
		return usuarioAtivo;
	}

	public void setUsuarioAtivo(boolean usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UfEnum getUf() {
		return uf;
	}

	public void setUf(UfEnum uf) {
		this.uf = uf;
	}

	/**
	 * Método responsável por incluir os dados de um objeto usuario em um objeto 
	 * herdado sem sobrescrever as informações específicas de já esxistentes.
	 * 
	 * @param Pessoa O pessoa que será copiada.
	 */
	public void addPessoa(Pessoa pessoa) {
		this.setId(pessoa.getId());
		this.setUsuarioAtivo(pessoa.isUsuarioAtivo());		
		if(null == this.getNome()){this.setNome(pessoa.getNome());}
		if(null == this.getSexo()){this.setSexo(pessoa.getSexo());}
		if(null == this.getEmail()){this.setEmail(pessoa.getEmail());}
		if(null == this.getLogin()){this.setLogin(pessoa.getLogin());}
		if(null == this.getSenha()){this.setSenha(pessoa.getSenha());}
		if(null == this.getLogradouro()){this.setLogradouro(pessoa.getLogradouro());}
		if(null == this.getNumero()){this.setNumero(pessoa.getNumero());}
		if(null == this.getComplemento()){this.setComplemento(pessoa.getComplemento());}
		if(null == this.getBairro()){this.setBairro(pessoa.getBairro());}
		if(null == this.getCidade()){this.setCidade(pessoa.getCidade());}
		if(null == this.getUf()){this.setUf(pessoa.getUf());}
	}

}
