package com.consultorio.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.consultorio.listing.EspecialidadeMedicaListing;
import com.consultorio.model.EspecialidadeMedica;

@Named
@ViewScoped
public class EspecialidadeMedicaController extends Controller<EspecialidadeMedica> {

	private static final long serialVersionUID = 4668254652883917403L;

	@Override
	public EspecialidadeMedica getEntity() {
		if(entity == null) {
			entity = new EspecialidadeMedica();
		}
		return entity;
	}
	public void abrirEspecialidadeListing() {
		EspecialidadeMedicaListing listing = new EspecialidadeMedicaListing();
		listing.open();
	}
	
	public void obterEspecialidadeListing(SelectEvent event) {
		EspecialidadeMedica entity = (EspecialidadeMedica) event.getObject();
		setEntity(entity);
	}
}
