package com.hortifruti.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hortifruti.application.JPAUtil;
import com.hortifruti.model.domain.Pessoa;

public class PessoaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(PessoaDAO.class.getName());
	
	private Dao<Pessoa> dao;

	public PessoaDAO() {
		dao = new DaoImpl<Pessoa>(Pessoa.class);
	}
	
	public Dao<Pessoa> getDao() {
		return dao;
	}
	
	
	public Pessoa obterUsuario(Pessoa usuario) {
		
		Pessoa pessoa = null;
		Session session = JPAUtil.getSession();
		
		try {
			
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pessoa.class);
			criteria.add(Restrictions.eq("pessoa.login", usuario.getLogin()));
			criteria.add(Restrictions.eq("pessoa.senha", usuario.getSenha()));
			
			pessoa = (Pessoa) criteria.uniqueResult();
			
		}catch(NoResultException e){
			session.getTransaction().rollback();
			LOGGER.error("Falha ao obter pessoa!", e);
		} catch (Exception e) {
			session.getTransaction().rollback();
			LOGGER.error("Falha ao obter pessoa!", e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}	
		
		return pessoa;
	}
	

	@SuppressWarnings("unchecked")
	public List<Pessoa> getPesquisaUsuario(Pessoa usuario) {
		List<Pessoa> listaUsuario;
		Session session = JPAUtil.getSession();
		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT p from Pessoa p "); 
			sql.append("left join p.perfil as perfil ");
			sql.append("where perfil.nome = :nomePerfil ");
			sql.append(" and p.usuarioAtivo = 1");
			if( null != usuario.getNome() && !"".equals(usuario.getNome().trim())){
				sql.append("	and p.nome like :nome ");
			}
			if( null != usuario.getEmail() && !"".equals(usuario.getEmail().trim())){
				sql.append("	and p.email like :email ");
			}
			
			Query query = session.createQuery(sql.toString());
			
			query.setParameter("nomePerfil", usuario.getPerfil().getNome());
			
			if( null != usuario.getNome() && !"".equals(usuario.getNome())){
				query.setParameter("nome", "%" + usuario.getNome().trim() + "%");
			}
			if( null != usuario.getEmail() && !"".equals(usuario.getEmail())){
				query.setParameter("email", "%" + usuario.getEmail().trim() + "%");
			}
					
			listaUsuario = (List<Pessoa>) query.list();
			return listaUsuario;

		} catch (NoResultException e) {
			return null;
		}
	}
	
}
