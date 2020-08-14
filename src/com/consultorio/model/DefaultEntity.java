package com.consultorio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.consultorio.validation.Validation;

@MappedSuperclass
public abstract class DefaultEntity<T> implements Serializable {

	private static final long serialVersionUID = -2883086999310776145L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	public abstract Validation<T> getValidation();
	
	@PrePersist
	private void atualizarDadosAntesInsert() {
		this.dataCadastro = new Date();
		this.dataAlteracao = this.dataCadastro;
	}
	
	@PreUpdate
	private void atualizarDadosAntesUpdate() {
		this.dataAlteracao = new Date();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
}
