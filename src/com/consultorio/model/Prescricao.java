package com.consultorio.model;

import javax.persistence.Entity;

import com.consultorio.validation.Validation;

@Entity
public class Prescricao extends DefaultEntity<Prescricao> {

	private static final long serialVersionUID = 5667175255157124925L;
	
	private String descricao;
	private String posologia;
	private Tipo tipo;
	private Medicamento medicamento;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	@Override
	public Validation<Prescricao> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}

}
