package com.hortifruti.application;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesContextUtil {

	private static FacesContextUtil contextUtil;

	private FacesContextUtil() {}
	

	public static FacesContextUtil getInstance() {
		if (contextUtil == null) {
			contextUtil = new FacesContextUtil();
		}
		return contextUtil;
	}

	public void setMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}

	public void setMensagemInfo(String mensagem) {
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public void setMensagemFatal(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, null));
	}

	public void setMensagemAviso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}
}
