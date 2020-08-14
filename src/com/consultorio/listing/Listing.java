package com.consultorio.listing;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import com.consultorio.factory.JPAFactory;
import com.consultorio.model.DefaultEntity;

public abstract class Listing<T extends DefaultEntity<T>> implements Serializable {

	private static final long serialVersionUID = 8479261041861677730L;
	
	private Class clazz = null;
	
	public Listing(Class clazz) {
		this.clazz = clazz;
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		T entity = (T) em.find(clazz, id);
		PrimeFaces.current().dialog().closeDynamic(entity);
	}

}
