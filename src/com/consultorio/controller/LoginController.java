package com.consultorio.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.consultorio.application.Util;
import com.consultorio.factory.JPAFactory;
import com.consultorio.model.Administrador;
import com.consultorio.model.Medico;
import com.consultorio.model.Paciente;
import com.consultorio.model.Pessoa;

@Named
@RequestScoped
public class LoginController extends Controller<Pessoa> {

	private static final long serialVersionUID = -7481072779220340737L;

	private Pessoa pessoa;
	UsuarioController usCon = new UsuarioController();

	public String logar() {
		pessoa = null;
		FacesContext context = FacesContext.getCurrentInstance();
		String senhacrip = Util.hashSHA256(getEntity().getSenha());
		System.out.println("Email: " + entity.getEmail() + " senha: " + senhacrip);
		pessoa = usCon.logar(entity.getEmail(), senhacrip);
		if(pessoa != null) {
			// adicionar um usuario na sessao
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("usuarioLogado", pessoa);
			
			Util.addMessageError("Login Efetuado com sucesso");
			return "home.xhtml?faces-redirect=true";
		}
		Util.addMessageError("Email ou senha incorretos");
		return " . ";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
	public boolean isLogado() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		boolean check;
		
		if (pessoa != null) {
			check = true;
		}
		else {
			check = false;
		}
		
		return check;
	}
	
	public boolean permiteAcesso(String nome) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		
		Paciente paciente = new Paciente();
		Medico medico = new Medico();
		Administrador adm = new Administrador();
		
		if(pessoa.getAdm() != null && nome.equalsIgnoreCase("Adm")) {
			return true;
		}
		else if (pessoa.getMedico() != null && nome.equalsIgnoreCase("Medico")) {
			return true;
		}
		else if ((pessoa.getAdm() != null || pessoa.getMedico() != null)
				&& nome.equalsIgnoreCase("Adm-Medico")) {
			return true;
		}
		else if (pessoa.getPaciente() != null && nome.equalsIgnoreCase("Paciente")) {
			return true;
		}
		else if ((pessoa.getAdm() != null || pessoa.getPaciente() != null)
				&& nome.equalsIgnoreCase("Adm-Paciente")) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean verificaUser(Integer id) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		
		Paciente paciente = new Paciente();
		Medico medico = new Medico();
		Administrador adm = new Administrador();
		
		if((pessoa.getClass().isInstance(paciente) && pessoa.getId().equals(id)) ||
				pessoa.getClass().isInstance(adm)) {
			return true;
		}
		else if ((pessoa.getClass().isInstance(medico) && pessoa.getId().equals(id)) ||
				pessoa.getClass().isInstance(adm)){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String paginaUser(Integer id) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		
		Paciente paciente = new Paciente();
		Medico medico = new Medico();
		Administrador adm = new Administrador();
		
		if((pessoa.getAdm() == null && pessoa.getId().equals(id))) {
			return "perfilUsuario.xhtml?faces-redirect=true";
		}
		else {
			return "perfilAdm.xhtml?faces-redirect=true";
		}
		
	}

	public Pessoa getPessoa(String email, String senha) {
		try {
			EntityManager em = JPAFactory.getEntityManager();
			Query query = em
					.createQuery("SELECT p FROM Pessoa p where p.email = :email and p.senha = :senha");
			query.setParameter("email", "email");
			query.setParameter("senha", "senha");
			return entity;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public Pessoa getEntity() {
		if (entity == null)
			entity = new Pessoa();
		return entity;
	}

}
