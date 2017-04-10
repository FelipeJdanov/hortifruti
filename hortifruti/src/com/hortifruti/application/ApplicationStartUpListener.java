package com.hortifruti.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class InitializeListener
 * 
 * Listener evocado ao iniciar a aplicação. 
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
    	LOGGER.info("Evocado o método Destroyer da aplicação");
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	//LOGGER.info("Evocando o método Initialized da aplicação.");
    	//Aqui é chamado o método que cria uma sessão do BD ao iniciar a aplicação, 
    	//garantindo que possíveis alteraçãoe feitas através do hibernate sejam aplicadas.
         JPAUtil.getSession();
    }
	
}
