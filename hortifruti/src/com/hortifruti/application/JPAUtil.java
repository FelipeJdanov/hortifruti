package com.hortifruti.application;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

/**
 * Classe respons�vel pelo controle de sess�o de entidade.
 *
 */
public class JPAUtil {

	static final Logger LOGGER = LogManager.getLogger(JPAUtil.class.getName());
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	static {
		try {
			entityManagerFactory = getEntityManagerFactory();
		} catch (Throwable e) {
			LOGGER.error("Erro ao inicializar a f�briaca de sess�o do database", e);
			throw new ExceptionInInitializerError(e);
		}
	}
	

	public static EntityManagerFactory getEntityManagerFactory() {
		
		if (null == entityManagerFactory) {
			entityManagerFactory = Persistence.createEntityManagerFactory("hortifruti");
			LOGGER.info("Sess�o de conex�o do database criada com sucesso.");
			return entityManagerFactory;
		}
		return entityManagerFactory;
		
	}
	
	public static Session getSession() {
		return entityManagerFactory.createEntityManager().unwrap(Session.class);
	}

}
