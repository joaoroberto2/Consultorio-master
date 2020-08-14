package com.consultorio.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.consultorio.application.Session;
import com.consultorio.model.Pessoa;

@Named
@ViewScoped
public class SessaoController implements Serializable {

	private static final long serialVersionUID = -7059310379031674540L;
	
	private Pessoa pessoaLogado = null;

	public Pessoa getPessoaLogado() {
		if(pessoaLogado == null)
			pessoaLogado = (Pessoa) Session.getInstance().getAttribute("pessoaLogado");
		return pessoaLogado;
	}

	public void setPessoaLogado(Pessoa pessoaLogado) {
		this.pessoaLogado = pessoaLogado;
	}
	
	public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
	

}
