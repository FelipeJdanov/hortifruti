package com.hortifruti.model.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hortifruti.model.domain.Pessoa;

@ManagedBean(name="usuarioLogado")
@SessionScoped
public class UsuarioLogadoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa usuario;

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	
}
