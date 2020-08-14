package com.consultorio.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import com.consultorio.application.RepositoryException;
import com.consultorio.application.Util;
import com.consultorio.application.ValidationException;
import com.consultorio.listing.EspecialidadeMedicaListing;
import com.consultorio.model.Agenda;
import com.consultorio.model.EspecialidadeMedica;
import com.consultorio.model.Medico;
import com.consultorio.model.Pessoa;
import com.consultorio.repository.EspecialidadeMedicaRepository;
import com.consultorio.repository.MedicoRepository;

@Named
@ViewScoped
public class MedicoController extends Controller<Pessoa> {

	private static final long serialVersionUID = -3862624597114610891L;
	
	private String filtro;
	private List<Pessoa> listaMedico;
	private List<Agenda> agendas = new ArrayList<Agenda>();
	private EspecialidadeMedica especialidadeMedica;
	private List<EspecialidadeMedica> listaEspecialidade;
	private List<EspecialidadeMedica> especialidades;
	private List<EspecialidadeMedica> cloneEspecialidade;
	private List<Pessoa> lista;
	
	public void pesquisar() {
		MedicoRepository repo = new MedicoRepository();
		listaMedico = repo.findByNome(getFiltro());
	}
	
	public void findAll() {
		MedicoRepository repo = new MedicoRepository();
		listaMedico = repo.findAll();
	}
	
	public void pesquisarAgenda() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		super.editar(pessoa.getId());
		MedicoRepository repo = new MedicoRepository();
		agendas = repo.pesquisarAgenda(getEntity().getId(), getEntity().getMedico().getId());
	}
	
	public String retornarData() {
		
		Date data = new Date();
		
		String dia = "10";
		String mes = "08";
		String ano = "2020";
		
		Locale localeBR = new Locale("pt", "BR");
        
		SimpleDateFormat fmt = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", localeBR);
		
		return fmt.format(new Date());
	}
	
	@Override
	public void salvar() {
		MedicoRepository r = new MedicoRepository();
		try {
			r.beginTransaction();
			getEntity().setSenha(Util.hashSHA256(getEntity().getSenha()));
			if (getEntity().getMedico().getListaEspecialidade() != null 
					&& getEntity().getMedico().getListaEspecialidade() == null) {
				getEntity().getMedico().setListaEspecialidade(null);
			}
			getEntity().getMedico().setListaEspecialidade(mandarSelecionados());
			r.salvar(getEntity());
			r.commitTransaction();
		} catch (RepositoryException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Problema ao salvar.");
			return;
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			r.rollbackTransaction();
			Util.addMessageError(e.getMessage());
			return;
		}
		limpar();
		Util.addMessageInfo("Cadastro realizado com sucesso.");
	}
	
	@Override
	public void editar(int id) {
		super.editar(id);
		if (entity.getMedico() == null) {
			entity.setMedico(new Medico());
			entity.getMedico().setListaEspecialidade(new ArrayList<EspecialidadeMedica>());
		}
	}
	
//	public void abrirMedicoListing() {
//		MedicoListing listing = new MedicoListing();
//		listing.open();
//	}
//	
//	public void obterMedicoListing(SelectEvent event) {
//		Medico entity = (Medico) event.getObject();
//		getEntity().setMedico(entity);
//	}
	
	public void abrirEspecialidadeListing() {
		EspecialidadeMedicaListing listing = new EspecialidadeMedicaListing();
		listing.open();
	}
	
	public void obterEspecialidadeListing(SelectEvent event) {
		EspecialidadeMedica entity = (EspecialidadeMedica) event.getObject();
		listaEspecialidade.add(entity);
		getEntity().getMedico().setListaEspecialidade(listaEspecialidade);
	}

	@Override
	public Pessoa getEntity() {
		if(entity == null) {
			entity = new Pessoa();
			if(entity.getMedico() == null) {
				entity.setMedico(new Medico());
				if (entity.getMedico().getListaEspecialidade() == null) {
					entity.getMedico().setListaEspecialidade(new ArrayList<EspecialidadeMedica>());
				}
			}
		}
	
		return entity;
	}

	public List<EspecialidadeMedica> gerarEspecialidade() {
		try {
			especialidades = new ArrayList<EspecialidadeMedica>();
			EspecialidadeMedicaRepository repo = new EspecialidadeMedicaRepository();
			especialidades = repo.findAll();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Util.addMessageError(e.getMessage());
		}
		return especialidades;
	}
	
	public List<EspecialidadeMedica> mandarSelecionados() {
		
		cloneEspecialidade = new ArrayList<EspecialidadeMedica>();
		
			for (int j = 0; j < entity.getMedico().getListaEspecialidade().size(); j++) {
				for (int i = 0; i < especialidades.size(); i++) {
				if (especialidades.get(i).getId().equals(entity.getMedico().getListaEspecialidade().get(j))) {
					cloneEspecialidade.add(especialidades.get(i));
				}
			}
				gerarEspecialidade();
		}

		listaEspecialidade = cloneEspecialidade;
		

		return listaEspecialidade;
	}
	
	public List<Pessoa> retornarEspecialidadesPorId(Integer id) {
		lista = new ArrayList<Pessoa>();
		MedicoRepository repo = new MedicoRepository();
		try {
			lista = repo.findByEspecialidade(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Util.addMessageError(e.getMessage());
		}
		return lista;
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Pessoa> getListaMedico() {
		if(listaMedico == null)
			listaMedico = new ArrayList<Pessoa>();
		return listaMedico;
	}

	public void setListaMedico(List<Pessoa> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public EspecialidadeMedica getEspecialidadeMedica() {
		return especialidadeMedica;
	}

	public void setEspecialidadeMedica(EspecialidadeMedica especialidadeMedica) {
		this.especialidadeMedica = especialidadeMedica;
	}

	public List<EspecialidadeMedica> getListaEspecialidade() {
		return listaEspecialidade;
	}

	public void setListaEspecialidade(List<EspecialidadeMedica> listaEspecialidade) {
		this.listaEspecialidade = listaEspecialidade;
	}

	public List<EspecialidadeMedica> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeMedica> especialidades) {
		this.especialidades = especialidades;
	}

	public List<EspecialidadeMedica> getCloneEspecialidade() {
		return cloneEspecialidade;
	}

	public void setCloneEspecialidade(List<EspecialidadeMedica> cloneEspecialidade) {
		this.cloneEspecialidade = cloneEspecialidade;
	}

	public List<Pessoa> getLista() {
		return lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

}
