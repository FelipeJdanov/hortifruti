package com.hortifruti.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface Dao<T> {
	
	public abstract void salvar(T bean);
	public abstract void atualizar(T bean);
	public abstract void excluir(T bean);
	public abstract T getBean(Long codigo);
	public abstract List<T>listarTodos();
	public abstract List<T>getBeansByExample(T bean);
}
