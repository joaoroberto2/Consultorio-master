package com.consultorio.model;

import java.util.List;

import javax.persistence.*;

import com.consultorio.validation.Validation;

@Entity
public class EspecialidadeMedica extends DefaultEntity<EspecialidadeMedica> {

	private static final long serialVersionUID = -6647418779683681549L;

	private String nome;
	
	private List<Medico> listaMedico;

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Validation<EspecialidadeMedica> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
