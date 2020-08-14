package com.consultorio.repository;

import java.util.List;

import javax.persistence.Query;

import com.consultorio.model.Agenda;
import com.consultorio.model.Pessoa;

public class AgendaRepository extends Repository<Pessoa> {

	public List<Agenda> listarTodos() {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("ag ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Agenda ag on pa.agenda.id = ag.id ");

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}

	public List<Agenda> listarAgendaMedPorId(Integer idPessoa) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("ag ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Medico me on pe.medico.id = me.id ");
		jpql.append("Inner Join Agenda ag on me.agenda.id = ag.id ");
		jpql.append("Where pe.id = "+idPessoa);
		
		Query query = getEntityManager().createQuery(jpql.toString());
		
		return query.getResultList();
	}

	public List<Agenda> listarAgendaPacientePorId(Integer idPessoa) {
		StringBuffer jpql = new StringBuffer();
 		jpql.append("SELECT ");
		jpql.append("ag ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Agenda ag on pa.agenda.id = ag.id ");
		jpql.append("Where pe.id = "+idPessoa);
		 
		Query query = getEntityManager().createQuery(jpql.toString());
		 
		return query.getResultList();
	}

	public Agenda pegarAgendaPacientePorId(Integer idPessoa, Integer idAgenda) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("ag ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Agenda ag on pa.agenda.id = ag.id ");
		jpql.append("Where pe.id = "+idPessoa);
		
		Query query = getEntityManager().createQuery(jpql.toString());
	
		return (Agenda) query.getSingleResult();
	}

	public Pessoa pegarMedicoPorAgendaId(Integer idAgenda) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("pe ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Medico me on pe.paciente.id = me.id ");
		jpql.append("Inner Join Agenda ag on me.agenda.id = ag.id ");
		jpql.append("Where ag.id = "+idAgenda);
		
		Query query = getEntityManager().createQuery(jpql.toString());
		
		return (Pessoa) query.getSingleResult();
	}

	public Pessoa pegarPacientePorAgendaId(Integer idAgenda) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("pe ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Agenda ag on pa.agenda.id = ag.id ");
		jpql.append("Where ag.id = " +idAgenda);
		
		Query query = getEntityManager().createQuery(jpql.toString());
		
		return (Pessoa) query.getSingleResult();
	}

	public static void salvar(Agenda agenda) {
		// TODO Auto-generated method stub

	}

	public static void remover(Agenda agenda) {
		// TODO Auto-generated method stub

	}

}
