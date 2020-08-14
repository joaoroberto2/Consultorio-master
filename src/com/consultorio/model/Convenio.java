package com.consultorio.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.consultorio.validation.Validation;

@Entity
public class Convenio extends DefaultEntity<Convenio> {

	private static final long serialVersionUID = 3189926229081725200L;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idconvenio")
	private List<ConvenioF> convenioF;
	
	private String carteirinha;
	private String plano;
	private Date validade;

	public List<ConvenioF> getConvenioF() {
		return convenioF;
	}

	public void setConvenioF(List<ConvenioF> convenioF) {
		this.convenioF = convenioF;
	}

	public String getCarteirinha() {
		return carteirinha;
	}

	public void setCarteirinha(String carteirinha) {
		this.carteirinha = carteirinha;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	@Override
	public Validation<Convenio> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}
}
