package com.consultorio.listing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import com.consultorio.factory.JPAFactory;
import com.consultorio.model.EspecialidadeMedica;

import com.consultorio.repository.EspecialidadeMedicaRepository;

@Named
@ViewScoped
public class EspecialidadeMedicaListing extends Listing<EspecialidadeMedica> {

	private static final long serialVersionUID = 1854020310735604097L;
	private List<EspecialidadeMedica> list;
	private String filtro;
	
	public EspecialidadeMedicaListing() {
		super(EspecialidadeMedica.class);
	}

	public void open() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", true);
		options.put("draggable", true);
		options.put("modal", true);
		options.put("width", 800);
		options.put("height", 500);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");

		PrimeFaces.current().dialog().openDynamic("especialidademedicalisting", options, null);
	}

	public void pesquisar() {
		EspecialidadeMedicaRepository repo = new EspecialidadeMedicaRepository();
		setList(repo.findByNome(getFiltro()));
	}


	public List<EspecialidadeMedica> getList() {
		if (list == null)
			list = new ArrayList<EspecialidadeMedica>();
		return list;
	}

	public void setList(List<EspecialidadeMedica> list) {
		this.list = list;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
