package com.consultorio.model;

import javax.persistence.Entity;

import com.consultorio.validation.Validation;

@Entity
public class Telefone extends DefaultEntity<Telefone> {
	
	private static final long serialVersionUID = 4741181020229599799L;
	
	private String ddd;
	private String numero;
	
	public String getDdd() {
		return ddd;
	}
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public Validation<Telefone> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}
}
