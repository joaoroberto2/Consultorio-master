package com.consultorio.model;

import java.util.Date;

import javax.persistence.Entity;

import com.consultorio.validation.Validation;

@Entity
public class Agenda extends DefaultEntity<Agenda>{

	private static final long serialVersionUID = 3630079033623890740L;
	
	private String nome;
	private Date atendimento;
	private Date horario;
	private Paciente paciente;
	private Telefone telefone;
	private Convenio convenio;
	private String observacao;
	private Boolean retorno;
	private Medico medico;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Date atendimento) {
		this.atendimento = atendimento;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getRetorno() {
		return retorno;
	}

	public void setRetorno(Boolean retorno) {
		this.retorno = retorno;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public Validation<Agenda> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}

}
