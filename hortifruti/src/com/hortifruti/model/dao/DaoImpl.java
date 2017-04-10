package com.hortifruti.model.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import com.hortifruti.application.BusinessException;
import com.hortifruti.application.JPAUtil;

public class DaoImpl<T> implements Dao<T>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DaoImpl.class.getName());
	
	private final Class<?> classe;
	
	public DaoImpl(Class<?> classe) {
		this.classe = classe;
	}

	public void salvar(T bean) {
		Session session = JPAUtil.getSession();

		try{
			session.beginTransaction();
			session.save(bean);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("Erro ao efetuar cadastro.", e);
			throw new BusinessException("Erro ao efetuar cadastro.");
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}	
	}

	public void atualizar(T bean) {
		Session session = JPAUtil.getSession();

		try{
			session.beginTransaction();
			session.update(bean);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("Erro ao atualizar cadastro.", e);
			throw new BusinessException("Erro ao atualizar cadastro.");
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void excluir(T bean) {
		Session session = JPAUtil.getSession();

		try{
			session.beginTransaction();
			session.delete(bean);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.error("Erro ao excluir cadastro.", e);
			throw new BusinessException("Erro ao excluir cadastro.");
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getBean(Long codigo) {
		T instancia = null;
		Session session = JPAUtil.getSession();
		try{
			session.beginTransaction();
			instancia = (T) session.get(classe,codigo);
		} catch (Exception e) {
			LOGGER.error("Erro ao erro ao obter "+ classe.getName(), e);
			throw new BusinessException("Erro ao erro ao obter "+ classe.getName());
		}finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return instancia;
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		Session session = JPAUtil.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select o from " + classe.getSimpleName() + " o" + " order by id desc");
		Query query = session.createQuery(sql.toString());
		return query.list();
	}

	public List<T> getBeansByExample(T bean) {
		Example example  = Example.create(bean);
		example.enableLike(MatchMode.START);
		example.ignoreCase();
		example.excludeZeroes();
		
		return null;
	}

}
