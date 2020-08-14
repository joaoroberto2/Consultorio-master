package com.consultorio.repository;

import java.util.List;

import javax.persistence.Query;

import com.consultorio.model.Endereco;
import com.consultorio.model.Pessoa;
import com.consultorio.model.Telefone;

public class AdministradorRepository extends Repository<Pessoa> {

	public List<Pessoa> findByNome(String nome) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" a ");
		jpql.append("FROM ");
		jpql.append("  Administrador a ");
		jpql.append("WHERE ");
		jpql.append(" upper(a.nome) like upper(:nome)");

		Query query = getEntityManager().createQuery(jpql.toString());

		query.setParameter("nome", "%" + nome + "%");

		return query.getResultList();
	}

	public List<Telefone> findTelefonesById(Integer id) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("p.telefone ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("Where p.id =" + id);

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}

	public List<Telefone> findByNumero(Integer id, String numero) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("tel ");
		jpql.append("FROM ");
		jpql.append("Telefone tel ");
		jpql.append("Inner Join Pessoa p on p.telefone.id = tel.id Where ");
		jpql.append(" p.id = "+id+" and upper(tel.numero) like upper(:numero) ");

		Query query = getEntityManager().createQuery(jpql.toString());

		query.setParameter("numero", "%" + numero + "%");

		return query.getResultList();
	}
	
	public Telefone editarTelefone(Integer idAdm, Integer idTel) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("tel ");
		jpql.append("FROM ");
		jpql.append("Telefone tel ");
		jpql.append("Inner Join Pessoa p on p.telefone.id = tel.id Where ");
		jpql.append(" p.id = "+idAdm+" and tel.id = "+idTel);

		Query query = getEntityManager().createQuery(jpql.toString());

		return (Telefone) query.getSingleResult();
	}

	public List<Pessoa> findAll() {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("p ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("Where p.adm <> null");

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

	public void excluirTel(Integer idAdm, Integer idTel) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("Delete ");
		jpql.append("FROM ");
		jpql.append("Telefone tel ");
		jpql.append("Inner Join Pessoa p on p.telefone.id = tel.id ");
		jpql.append("Where p.id = "+idAdm+ " and tel.id = "+idTel);	
		
		Query query = getEntityManager().createQuery(jpql.toString());

	}

	public Endereco editarEndereco(Integer id, Integer id2) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("p.endereco ");
		jpql.append("FROM ");
		jpql.append("Pessoa p Inner Join Endereco e on p.endereco.id = e.id ");
		jpql.append("Where ");
		jpql.append("p.id = "+id+" and e.id = "+id2);

		Query query = getEntityManager().createQuery(jpql.toString());

		return (Endereco) query.getSingleResult();
	}

}
