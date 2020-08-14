package com.consultorio.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Anamnese {

	private boolean alergia;
	private boolean asma;
	private boolean diabete;
	private boolean doencaCronica;
	private boolean hipertesao;
	private boolean neoplasia;
	private boolean remedioUsoContinuo;
	public boolean isAlergia() {
		return alergia;
	}
	public void setAlergia(boolean alergia) {
		this.alergia = alergia;
	}
	public boolean isAsma() {
		return asma;
	}
	public void setAsma(boolean asma) {
		this.asma = asma;
	}
	public boolean isDiabete() {
		return diabete;
	}
	public void setDiabete(boolean diabete) {
		this.diabete = diabete;
	}
	public boolean isHipertesao() {
		return hipertesao;
	}
	public void setHipertesao(boolean hipertesao) {
		this.hipertesao = hipertesao;
	}
	public boolean isNeoplasia() {
		return neoplasia;
	}
	public void setNeoplasia(boolean neplasia) {
		this.neoplasia = neplasia;
	}
	public boolean isDoencaCronica() {
		return doencaCronica;
	}
	public void setDoencaCronica(boolean doencaCronica) {
		this.doencaCronica = doencaCronica;
	}
	public boolean isRemedioUsoContinuo() {
		return remedioUsoContinuo;
	}
	public void setRemedioUsoContinuo(boolean remedioUsoContinuo) {
		this.remedioUsoContinuo = remedioUsoContinuo;
	}
	
	
	
	

}
