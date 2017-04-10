package com.hortifruti.model.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hortifruti.application.FacesContextUtil;
import com.hortifruti.model.business.UsuarioBusiness;
import com.hortifruti.model.domain.Pessoa;

@ViewScoped
@ManagedBean
public class LoginBean implements Serializable{

	/**	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(LoginBean.class);

	@ManagedProperty(value = "#{usuarioLogado}")
	private UsuarioLogadoBean usuarioLogadoBean;

	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

	private Pessoa usuario = new Pessoa();

	private Boolean telaRecuperarSenha = false;

	public String logar() {
		try {
			Pessoa usuario;
			usuario = usuarioBusiness.obterUsuario(this.usuario);
			if (usuario != null) {
				usuarioLogadoBean.setUsuario(usuario);
				return "menu";
			} else {
				FacesContextUtil.getInstance().setMensagemErro("Usuário ou senha incorretos!");
				return "login";
			}
		} catch (Throwable e) {
			FacesContextUtil.getInstance().setMensagemErro("Ocorreu uma falha no login");
			LOGGER.error("Ocorreu uma falha no login", e);
			return "login";
		}

	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login";

	}
	
	public void esqueciSenha() {
		setTelaRecuperarSenha(true);
	}

	public void voltar() {
		setTelaRecuperarSenha(false);
	}

	public Boolean getTelaRecuperarSenha() {
		return telaRecuperarSenha;
	}

	public void setTelaRecuperarSenha(Boolean telaRecuperarSenha) {
		this.telaRecuperarSenha = telaRecuperarSenha;
	}

	public UsuarioLogadoBean getUsuarioLogadoBean() {
		return usuarioLogadoBean;
	}

	public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
		this.usuarioLogadoBean = usuarioLogadoBean;
	}
	
	
	public Pessoa getUsuario() {
		return usuario;
	}
	
	
	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

}
