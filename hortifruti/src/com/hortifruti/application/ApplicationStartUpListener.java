package com.hortifruti.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class InitializeListener
 * 
 * Listener evocado ao iniciar a aplica��o. 
 *
 */
@WebListener
public class ApplicationStartUpListener implements ServletContextListener {
	
	private static final Logger LOGGER = LogManager.getLogger(ApplicationStartUpListener.class.getName());
	
    /**
     * Default constructor. 
     */
    public ApplicationStartUpListener() {}

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	LOGGER.info("Evocado o m�todo Destroyer da aplica��o");
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	//LOGGER.info("Evocando o m�todo Initialized da aplica��o.");
    	//Aqui � chamado o m�todo que cria uma sess�o do BD ao iniciar a aplica��o, 
    	//garantindo que poss�veis altera��oe feitas atrav�s do hibernate sejam aplicadas.
         JPAUtil.getSession();
    }
	
}
