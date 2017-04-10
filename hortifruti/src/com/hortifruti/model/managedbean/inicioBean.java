package com.hortifruti.model.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hortifruti.application.FacesContextUtil;

@ViewScoped
@ManagedBean
public class inicioBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(inicioBean.class);

	public void loadIndex() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginaInicial.jsf");
		} catch (IOException e) {
			FacesContextUtil.getInstance().setMensagemErro("Erro!");
			LOGGER.error(e);
		}
	}
	
}
