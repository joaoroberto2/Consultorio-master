package com.consultorio.controller;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.consultorio.application.RepositoryException;
import com.consultorio.application.Util;
import com.consultorio.application.ValidationException;
import com.consultorio.factory.JPAFactory;
import com.consultorio.model.DefaultEntity;
import com.consultorio.repository.Repository;

public abstract class Controller <T  extends DefaultEntity<T>> implements Serializable {

	private static final long serialVersionUID = -6924365579568836276L;
	protected T entity;
	
	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Controller() {
		super();
	}

	public void salvar() {
		Repository<T> r = new Repository<T>();
		try {
			r.beginTransaction();
			r.salvar(getEntity());
			r.commitTransaction();	
		} catch (RepositoryException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Problema ao salvar.");
			return;
		}catch(ValidationException e) {
			System.out.println(e.getMessage());
			r.rollbackTransaction();
			Util.addMessageError(e.getMessage());
			return;
		}
		limpar();
		Util.addMessageInfo("Cadastro realizado com sucesso.");
	}

	public void excluir() {
		Repository<T> r = new Repository<T>();
		try {
			r.beginTransaction();
			r.excluir(getEntity());
			r.commitTransaction();	
		} catch (RepositoryException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Problema ao excluir.");
			return;
		}
		limpar();
		Util.addMessageInfo("Exclusão realizada com sucesso.");	
	}
	
	public void editar(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((T) em.find(getEntity().getClass(), id));
	}
	
	public void limpar() {
		entity = null;
	}
}
