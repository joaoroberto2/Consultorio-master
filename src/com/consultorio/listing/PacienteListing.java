package com.consultorio.listing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.consultorio.model.Pessoa;
import com.consultorio.repository.PacienteRepository;

@Named
@ViewScoped
public class PacienteListing extends Listing<Pessoa> {

	private static final long serialVersionUID = -5274842308492045809L;
	private List<Pessoa> list;
	private String filtro;
	
	public PacienteListing() {
		super(Pessoa.class);
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

		PrimeFaces.current().dialog().openDynamic("pacientelisting", options, null);
	}

	public void pesquisar() {
		PacienteRepository repo = new PacienteRepository();
		list = repo.findByNome(getFiltro());
	}

	public List<Pessoa> getList() {
		if (list == null)
			list = new ArrayList<Pessoa>();
		return list;
	}

	public void setList(List<Pessoa> list) {
		this.list = list;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
