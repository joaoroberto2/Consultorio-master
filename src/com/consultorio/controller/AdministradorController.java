package com.consultorio.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.consultorio.application.RepositoryException;
import com.consultorio.application.Util;
import com.consultorio.application.ValidationException;
import com.consultorio.model.Administrador;
import com.consultorio.model.Endereco;
import com.consultorio.model.Pessoa;
import com.consultorio.model.Telefone;
import com.consultorio.repository.AdministradorRepository;
import com.google.gson.Gson;

@Named
@ViewScoped
public class AdministradorController extends Controller<Pessoa> {

	private static final long serialVersionUID = -3862624597114610891L;
	
	private String filtro;
	private List<Pessoa> listaAdministrador;
	private List<Telefone> listaTelefones;
	private Telefone telefone = new Telefone();
	private Endereco endereco = new Endereco();
	
	public void pesquisar() {
		AdministradorRepository repo = new AdministradorRepository();
		listaAdministrador = repo.findByNome(getFiltro());
	}

	
	@Override
	public void editar(int id) {
		super.editar(id);
	}
	
	@Override
	public void salvar() {
		AdministradorRepository r = new AdministradorRepository();
		try {
			r.beginTransaction();
			getEntity().setSenha(Util.hashSHA256(getEntity().getSenha()));
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
	
//  								TELEFONE  
	
	public void pesquisarTelefone(Integer id) {
		AdministradorRepository repo = new AdministradorRepository();
		listaTelefones = repo.findByNumero(id, getFiltro());
	}
	
	public List<Telefone> listaTelefone(Integer id) {
		AdministradorRepository repo = new AdministradorRepository();
		listaTelefones = repo.findTelefonesById(id);
		
		return listaTelefones;
	}
	
	public void editarTelefone(Integer idAdm, Integer idTelefone) {
		super.editar(idAdm);
		AdministradorRepository repo = new AdministradorRepository();
		telefone = repo.editarTelefone(idAdm, idTelefone);
	}
	
	public void excluirTel(Integer idAdm, Integer idTel) {
		super.editar(idAdm);
		for (int i = 0; i < getEntity().getTelefone().size(); i++) {
			if (getEntity().getTelefone().get(i).getId() == idTel) {
				getEntity().getTelefone().remove(i);
			}
		}
		
		AdministradorRepository r = new AdministradorRepository();
		try {
			r.beginTransaction();
			try {
				r.salvar(getEntity());
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			r.commitTransaction();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void limparTel() {
		telefone = new Telefone();
	}
	
	public void salvarTelefone(Integer id) {
		editar(id);
		AdministradorRepository r = new AdministradorRepository();
		try {
			r.beginTransaction();
			if (getEntity().getTelefone() == null) {
				getEntity().setTelefone(new ArrayList<Telefone>());
				getEntity().getTelefone().add(telefone);

			}
			if (getEntity().getTelefone() != null) {
				getEntity().getTelefone().add(telefone);
			}
			
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
		limparTel();
		Util.addMessageInfo("Telefone Adicionado com sucesso.");
	}
	
	
//                   			  ENDERECO                             
	
	public void pesquisarCep(AjaxBehaviorEvent event) {
		
		try {
			
			URL url = new URL("https://viacep.com.br/ws/" + endereco.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}

			Endereco end = new Gson().fromJson(jsonCep.toString(), Endereco.class);

			if (end.getCep() == null || end.getCep() == "") {
				Util.addMessageWarn("Cep inválido");
				limpar();
				limparEnd();
			} else {
				endereco.setCep(end.getCep());
				endereco.setLogradouro(end.getLogradouro());
				endereco.setComplemento(end.getComplemento());
				endereco.setBairro(end.getBairro());
				endereco.setLocalidade(end.getLocalidade());
				endereco.setUf(end.getUf());
			}
			
			
		} catch (Exception e) {
			Util.addMessageWarn("Cep inválido");
			limpar();
			limparEnd();
			return;
		}
		
	}
	
	public void salvarEndereco(Integer id) {
		editar(id);
		AdministradorRepository r = new AdministradorRepository();
		try {
			r.beginTransaction();
			if (getEntity().getEndereco() == null) {
				if (endereco.getUf() != null) {
					getEntity().setEndereco(endereco);
				} 
			} 
			else if (getEntity().getEndereco() != null) {
				if (endereco.getUf() != null) {
					getEntity().setEndereco(endereco);
				} 
				else {
					Util.addMessageWarn("Existem campos em branco");
					return;
				}			
			} 
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
		limparEnd();
		Util.addMessageInfo("Endereço Adicionado com sucesso.");
	}
	
	public boolean botaoSalvarEnd() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		super.editar(pessoa.getId());
		
		if(getEntity().getEndereco() == null) {
			return false;
		} 
		else {
			if (getEntity().getEndereco().getId() != null && endereco.getId() != null) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public void editarEndereco() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		super.editar(pessoa.getId());
		AdministradorRepository repo = new AdministradorRepository();
		endereco = repo.editarEndereco(pessoa.getId(), getEntity().getEndereco().getId());

	}
	
	public void limparEnd() {
		limpar();
		endereco = new Endereco();
	}
	
	public boolean botaoEditarEnd() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		super.editar(pessoa.getId());
		
		if(getEntity().getEndereco() == null) {
			return true;
		}
		else {
			if (getEntity().getEndereco().getId() != null && endereco.getId() != null) {
				return true;
			} else {
				return false;				
			}
		}
	}
	
//	public void abrirAdministradorListing() {
//		AdministradorListing listing = new AdministradorListing();
//		listing.open();
//	}
//	
//	public void obterAdministradorListing(SelectEvent event) {
//		Administrador entity = (Administrador) event.getObject();
//		getEntity().setAdministrador(entity);
//	}

	@Override
	public Pessoa getEntity() {
		if(entity == null) {
			entity = new Pessoa();
			if (entity.getAdm() == null) {
				entity.setAdm(new Administrador());
				entity.setTelefone(new ArrayList<Telefone>());
				entity.setEndereco(new Endereco());
			}
		}
		
		return entity;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Pessoa> getListaAdministrador() {
		if(listaAdministrador == null)
			listaAdministrador = new ArrayList<Pessoa>();
		return listaAdministrador;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void setListaAdministrador(List<Pessoa> listaAdministrador) {
		this.listaAdministrador = listaAdministrador;
	}

	public List<Telefone> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(List<Telefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
