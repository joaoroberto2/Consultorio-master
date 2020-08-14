package com.consultorio.model;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.consultorio.application.RepositoryException;
import com.consultorio.application.ValidationException;
import com.consultorio.repository.AdministradorRepository;
import com.consultorio.repository.EspecialidadeMedicaRepository;
import com.consultorio.validation.Validation;

@Entity
@WebListener
public class Administrador extends DefaultEntity<Administrador> implements ServletContextListener {

	private static final long serialVersionUID = 2164284968159609561L;

	@Override
	public Validation<Administrador> getValidation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		AdministradorRepository repo = new AdministradorRepository();
		EspecialidadeMedicaRepository esp = new EspecialidadeMedicaRepository();
		EspecialidadeMedica especialidade = new EspecialidadeMedica();
		EspecialidadeMedica especialidade2 = new EspecialidadeMedica();
		EspecialidadeMedica especialidade3 = new EspecialidadeMedica();
		EspecialidadeMedica especialidade4 = new EspecialidadeMedica();
		EspecialidadeMedica especialidade5 = new EspecialidadeMedica();
		EspecialidadeMedica especialidade6 = new EspecialidadeMedica();
		EspecialidadeMedica especialidade7 = new EspecialidadeMedica();
		
		Pessoa adm = new Pessoa();
		adm.setAdm(new Administrador());
		adm.setNome("Adm");
		adm.setEmail("adm");
		// senha adm
		adm.setSenha("86f65e28a754e1a71b2df9403615a6c436c32c42a75a10d02813961b86f1e428");
		adm.setCpf("544.874.400-17");
		adm.setDataNascimento(new GregorianCalendar(2000, 01, 01).getTime());

		especialidade.setNome("Cardiologista");
		especialidade2.setNome("Neurocirugião");
		especialidade3.setNome("Otorrinolaringologista");
		especialidade4.setNome("Ortopedia");
		especialidade5.setNome("Dermatologia");
		especialidade6.setNome("Radiologia");
		especialidade7.setNome("Cirurgia plástica");
		

		if (repo.findAll().isEmpty()) {
			try {
				repo.beginTransaction();
				try {
					repo.salvar(adm);
					repo.commitTransaction();
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (esp.findAll().isEmpty()) {
			try {
				esp.beginTransaction();
				try {
					esp.salvar(especialidade);
					esp.salvar(especialidade2);
					esp.salvar(especialidade3);
					esp.salvar(especialidade4);
					esp.salvar(especialidade5);
					esp.salvar(especialidade6);
					esp.salvar(especialidade7);
					esp.commitTransaction();
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}