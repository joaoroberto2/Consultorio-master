package com.consultorio.repository;

import javax.persistence.EntityManager;

import com.consultorio.application.RepositoryException;
import com.consultorio.application.ValidationException;
import com.consultorio.factory.JPAFactory;
import com.consultorio.model.Convenio;
import com.consultorio.model.ConvenioF;
import com.consultorio.model.DefaultEntity;
import com.consultorio.model.Telefone;

public class Repository<T extends DefaultEntity<T>> {
	private EntityManager entityManager;

	public Repository() {
		entityManager = JPAFactory.getEntityManager();
	}

	public Repository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void beginTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Problema ao " + "iniciar uma transação");
		}
	}

	public void commitTransaction() throws RepositoryException {
		try {
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Problema ao " + "comitar uma transação");
		}
	}

	public void rollbackTransaction() {
		try {
			getEntityManager().getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvar(T entity) throws RepositoryException, ValidationException{
		try {
			
			if(entity.getValidation() != null) {
				entity.getValidation().validate(entity);
			}
			getEntityManager().merge(entity);
		}
            catch (ValidationException e) {
		    // e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println("Erro no repositorio ao executar o método merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar.");
		}
	}

	public void excluir(T entity) throws RepositoryException {
		try {
			T obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
		} catch (Exception e) {
			System.out.println("Erro no repositorio " + "ao executar o método merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar.");
		}
	}
	
	public void excluir(Telefone entity) throws RepositoryException {
		try {
			Telefone obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
		} catch (Exception e) {
			System.out.println("Erro no repositorio " + "ao executar o método merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar.");
		}
	}
	
	public void excluirConvenio(Convenio entity) throws RepositoryException {
		try {
			Convenio obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
		} catch (Exception e) {
			System.out.println("Erro no repositorio " + "ao executar o método merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar.");
		}
	}
	
	public void excluirConvF(ConvenioF entity) throws RepositoryException {
		try {
			ConvenioF obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
		} catch (Exception e) {
			System.out.println("Erro no repositorio " + "ao executar o método merge.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao salvar.");
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
