package com.consultorio.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.consultorio.model.Administrador;
import com.consultorio.model.Medico;
import com.consultorio.model.Paciente;
import com.consultorio.model.Pessoa;

@WebFilter(filterName = "SecurityFilter", urlPatterns = { "/faces/*" })

public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Para desabilitar o filter, descomente as duas proximas linhas e comente o
		// restante
//		chain.doFilter(request, response);
//		return;

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Paciente paciente = new Paciente();
		Medico medico = new Medico();
		Administrador adm = new Administrador();

		// retorna a sessao corrente (false - para nao criar uma nova sessao)
		HttpSession session = (HttpSession) req.getSession();

		// carregando o atributo do tipo pessoa dentro da sessao
		Pessoa pessoa = (Pessoa) session.getAttribute("usuarioLogado");
		String link = req.getRequestURL().toString();
		// Permite os recursos css img etc
		if (link.contains("http://localhost:8080/Consultorio/faces/javax.faces.resource/")) {
			chain.doFilter(request, response);
			return;
		}
		// imprime o endereco da pagina
		System.out.println(link);

		// se nao tiver usuario logado na sessao so tera acesso a pagina de login, cadastro ou index etc
		if (pessoa == null && !(link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/login.xhtml")
				|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/cadastro.xhtml")
				|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/index.xhtml"))) {
			res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
			return;
		} // nao permite o usuario logado a acessar a acessar a area de login ou de cadastro etc
		else if (pessoa != null && (link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/login.xhtml")
				|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/cadastro.xhtml"))) {
			res.sendRedirect(req.getContextPath() + "/faces/home.xhtml");
			return;
		}

		// tratamento de páginas pelo tipo médico e paciente
		else if (pessoa != null && pessoa.getMedico() != null && pessoa.getPaciente() != null) {
			if (!(link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/medico.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/paciente.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/perfilAdm.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/especialidademedica.xhtml"))) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/faces/home.xhtml");
				return;
			}
		}
		// tratamento de páginas pelo tipo médico
		else if (pessoa != null && pessoa.getMedico() != null) {
			if (link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/home.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/perfilUsuario.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/index.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/home.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/prescricoes.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/atestado.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/agenda.xhtml")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/faces/home.xhtml");
				return;
			}
		}
		// tratamento de páginas pelo tipo paciente
		else if (pessoa != null && pessoa.getPaciente() != null) {
			if (link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/home.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/perfilUsuario.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/home.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/index.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/examePaciente.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/anamnese.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/evolucao.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/atestado.xhtml")
					|| link.equalsIgnoreCase("http://localhost:8080/Consultorio/faces/agenda.xhtml")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/faces/home.xhtml");
				return;
			}
		} else {
			// CONTINUA A EXECUCAO INDO PARA A PAGINA REQUISITADA
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
