package com.hortifruti.application;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 * Classe responsável pelo controle de sessão de entidade.
 *
 */
public class JPAUtil {

	static final Logger LOGGER = LogManager.getLogger(JPAUtil.class.getName());
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	static {
		try {
			entityManagerFactory = getEntityManagerFactory();
		} catch (Throwable e) {
			LOGGER.error("Erro ao inicializar a fábriaca de sessão do database", e);
			throw new ExceptionInInitializerError(e);
		}
	}
	

	public static EntityManagerFactory getEntityManagerFactory() {
		
		if (null == entityManagerFactory) {
			entityManagerFactory = Persistence.createEntityManagerFactory("hortifruti");
			LOGGER.info("Sessão de conexão do database criada com sucesso.");
			return entityManagerFactory;
		}
		return entityManagerFactory;
		
	}
	
	public static Session getSession() {
		return entityManagerFactory.createEntityManager().unwrap(Session.class);
	}

}
