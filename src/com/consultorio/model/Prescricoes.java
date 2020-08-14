package com.consultorio.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Prescricoes {
	private boolean industrializados;
	private boolean manipulados;
	private boolean composicoes;
	

	public boolean isIndustrializados() {
		return industrializados;
	}

	public void setIndustrializados(boolean industrializados) {
		this.industrializados = industrializados;
	}

	public boolean isManipulados() {
		return manipulados;
	}

	public void setManipulados(boolean manipulados) {
		this.manipulados = manipulados;
	}

	public boolean isComposicoes() {
		return composicoes;
	}

	public void setComposicoes(boolean composicoes) {
		this.composicoes = composicoes;
	}

}
