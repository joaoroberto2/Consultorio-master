package com.consultorio.model;

import java.util.Date;

import javax.persistence.Entity;

import com.consultorio.validation.Validation;

@Entity
public class Financeiro extends DefaultEntity<Financeiro> {

	private static final long serialVersionUID = -2424463964056181252L;
	
	private Paciente paciente;
	private String cpf;
	private String descricao;
	private Date data;
	private Double valor;
	private Medico medico;
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public Validation<Financeiro> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}

}
