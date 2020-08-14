package com.consultorio.repository;

import java.util.List;

import javax.persistence.Query;

import com.consultorio.model.Agenda;
import com.consultorio.model.EspecialidadeMedica;
import com.consultorio.model.Pessoa;

public class MedicoRepository extends Repository<Pessoa> {

	public List<Pessoa> findByNome(String nome) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT Distinct ");
		jpql.append("pe ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Medico me on pe.medico.id = me.id ");
		jpql.append("Inner Join EspecialidadeMedica es on me.listaEspecialidade.id = es.id and ");
		jpql.append("upper(pe.nome) like upper(:nome)");
		jpql.append("Order By pe.id");

		Query query = getEntityManager().createQuery(jpql.toString());

		query.setParameter("nome", "%" + nome + "%");

		return query.getResultList();
	}

	public List<Pessoa> findByEspecialidade(Integer id) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("es.nome ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Medico me on pe.medico.id = me.id ");
		jpql.append("Inner Join EspecialidadeMedica es on me.listaEspecialidade.id = es.id ");
		jpql.append("Where pe.id = " + id);

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}
	
	public boolean containsEmail(Integer id, String email) {
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

	public List<Agenda> pesquisarAgenda(Integer idPessoa, Integer idMed) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("ag ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Medico me on pe.medico.id = me.id ");
		jpql.append("Inner Join Agenda ag on me.agenda.id = ag.id and ");
		jpql.append("pe.id ="+idPessoa+" and me.id="+idMed);

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}

	public List<Pessoa> findAll() {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("me ");
		jpql.append("FROM ");
		jpql.append("Medico me ");

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}


}
