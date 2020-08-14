package com.consultorio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.consultorio.validation.Validation;

@Entity
public class Paciente extends DefaultEntity<Paciente> {
	
	private static final long serialVersionUID = -8722060693269364691L;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "paciente_atendimento", joinColumns = { @JoinColumn(name = "idpaciente") }, inverseJoinColumns = {
			@JoinColumn(name = "idatendimento") })
	private List<Atendimento> atendimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Convenio convenio;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "paciente_agenda", joinColumns = { @JoinColumn(name = "idpaciente") }, inverseJoinColumns = {
			@JoinColumn(name = "idagenda") })
	private List<Agenda> agenda;
	
	public List<Atendimento> getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(List<Atendimento> atendimento) {
		this.atendimento = atendimento;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	public List<Agenda> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	@Override
	public Validation<Paciente> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}
}
