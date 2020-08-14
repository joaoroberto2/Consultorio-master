package com.consultorio.repository;

import java.util.List;

import javax.persistence.Query;

import com.consultorio.model.Agenda;
import com.consultorio.model.Pessoa;

public class PacienteRepository extends Repository<Pessoa> {
	
	public List<Pessoa> findByNome(String nome){
		
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("pe ");
		jpql.append("FROM ");
		jpql.append("Paciente pa ");
		jpql.append("Inner Join Pessoa pe on pa.id = pe.paciente.id and ");
		jpql.append("upper(pe.nome) like upper(:nome)");
		
		Query query = getEntityManager().createQuery(jpql.toString());
		
		query.setParameter("nome", "%" + nome + "%");
		
		return query.getResultList();
	}

	public boolean containsEmail(String email, Integer id) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" count(*) ");
		jpql.append("FROM ");
		jpql.append("  Pessoa p ");
		jpql.append("WHERE ");
		jpql.append(" upper(p.email) = upper(?) ");
		jpql.append(" AND p.id <> ? ");

		Query query = getEntityManager().createNativeQuery(jpql.toString());

		query.setParameter(1, email);
		query.setParameter(2, id == null ? -1 : id);
		
		long resultado = (long) query.getSingleResult();
		
		return resultado == 0 ? false : true;

	}
	
	public boolean containsCpf(String cpf, Integer id) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" count(*) ");
		jpql.append("FROM ");
		jpql.append("  Pessoa p ");
		jpql.append("WHERE ");
		jpql.append(" upper(p.cpf) = upper(?) ");
		jpql.append(" AND p.id <> ? ");

		Query query = getEntityManager().createNativeQuery(jpql.toString());

		query.setParameter(1, cpf);
		query.setParameter(2, id == null ? -1 : id);
		
		long resultado = (long) query.getSingleResult();
		
		return resultado == 0 ? false : true;
	}
	
	public boolean containsRg(String rg, Integer id) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" count(*) ");
		jpql.append("FROM ");
		jpql.append("  Pessoa p ");
		jpql.append("WHERE ");
		jpql.append(" upper(p.rg) = upper(?) ");
		jpql.append(" AND p.id <> ? ");

		Query query = getEntityManager().createNativeQuery(jpql.toString());

		query.setParameter(1, rg);
		query.setParameter(2, id == null ? -1 : id);
		
		long resultado = (long) query.getSingleResult();
		
		return resultado == 0 ? false : true;
	}

	public List<Agenda> pesquisarAgenda() {
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
	
}
