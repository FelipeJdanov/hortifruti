package com.hortifruti.model.business;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hortifruti.enumeration.PerfilUsuarioEnum;
import com.hortifruti.model.dao.PessoaDAO;
import com.hortifruti.model.domain.Perfil;
import com.hortifruti.model.domain.Pessoa;



public class UsuarioBusiness implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(UsuarioBusiness.class);
	
	private PessoaDAO usuarioDAO = new PessoaDAO();
	
		
	public void salvarUsuario(Pessoa usuario){		
		try{
			usuarioDAO.getDao().salvar(usuario);
		}catch(Exception e){
			LOGGER.error("Falha ao salvar o Usuario",e);
			
		}	
	}
	
	public void atualizarUsuario(Pessoa pessoa){		
		try{			
			usuarioDAO.getDao().atualizar(pessoa);
		}catch(Exception e){
			LOGGER.error("Falha ao salvar Cliente",e);	
		}		
	}
	
	public Pessoa obterUsuario(Pessoa usuario)throws Throwable{	
			return usuarioDAO.obterUsuario(usuario);	
	}	
		
	public Pessoa obterPorId(Pessoa usuario) {
		Long idPessoa = new Long(usuario.getId());
		Pessoa pessoaEncontrada = usuarioDAO.getDao().getBean(idPessoa);
		pessoaEncontrada.setId(idPessoa);
		return pessoaEncontrada;
	}
	
	public List<Pessoa> consultar(Pessoa pessoa, PerfilUsuarioEnum tipoUsuario){
		Perfil perfil = new Perfil();
		perfil.setNome(tipoUsuario.getLabel().toUpperCase());
		pessoa.setPerfil(perfil);
		return usuarioDAO.getPesquisaUsuario(pessoa);
	}
	
	public void exclusaoLogica(Pessoa usuario) throws Exception{
		try{
			usuario = usuarioDAO.getDao().getBean(usuario.getId());
			usuario.setUsuarioAtivo(false);
			usuarioDAO.getDao().atualizar(usuario);
		}catch(Exception e){
			LOGGER.error("Falha ao excluir o Usuario",e);
			throw new Exception(e);
		}
	}
	
}
