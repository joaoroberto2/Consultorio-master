package com.consultorio.model;

import javax.persistence.Entity;

import com.consultorio.validation.Validation;

@Entity
public class Medicamento extends DefaultEntity<Medicamento> {

	private static final long serialVersionUID = -4432412034960896245L;
	
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Validation<Medicamento> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}
}
