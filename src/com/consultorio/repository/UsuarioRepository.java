package com.consultorio.repository;

import java.util.List;

import javax.persistence.Query;

import com.consultorio.model.Convenio;
import com.consultorio.model.ConvenioF;
import com.consultorio.model.Endereco;
import com.consultorio.model.EspecialidadeMedica;
import com.consultorio.model.Pessoa;
import com.consultorio.model.Telefone;

public class UsuarioRepository extends Repository<Pessoa> {

	public List<Pessoa> findByNome(Integer idUser, String nome) {

		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" p ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("WHERE ");
		jpql.append("p.id = :idUser");
		jpql.append("upper(p.nome) like upper(:nome)");

		Query query = getEntityManager().createQuery(jpql.toString());

		query.setParameter("idUser", idUser);
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
	
	public List<Telefone> existeTelefone(String ddd, String numero) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("tel ");
		jpql.append("FROM ");
		jpql.append("Telefone tel ");
		jpql.append("Where upper(tel.ddd) = upper(:ddd) and upper(tel.numero) = upper(:numero)");

		Query query = getEntityManager().createQuery(jpql.toString());
		
		query.setParameter("ddd", ddd);
		query.setParameter("numero", numero);

		return query.getResultList();
	}

	public void excluirTel(Integer idUser, Integer idTel) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("Delete ");
		jpql.append("FROM ");
		jpql.append("Telefone tel ");
		jpql.append("Where tel.id = "+idTel);	
		
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

	public List<EspecialidadeMedica> findByEspecialidade(Integer id) {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("es ");
			jpql.append("FROM ");
			jpql.append("Pessoa pe ");
			jpql.append("Inner Join Medico me on pe.medico.id = me.id ");
			jpql.append("Inner Join EspecialidadeMedica es on me.listaEspecialidade.id = es.id ");
			jpql.append("Where pe.id = " + id);

			Query query = getEntityManager().createQuery(jpql.toString());

			return query.getResultList();
		
	}
	
	public void excluirEsp(Integer idUser, Integer idMed,Integer idEsp) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("Delete ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("Inner Join Medico m on p.medico.id = m.id ");
		jpql.append("Inner Join EspecialidadeMedica esp on m.listaEspecialidade.id = esp.id ");
		jpql.append("Where p.id = "+idUser+ " and m.id = "+idMed+ " and esp.id = "+idEsp);	
		
		Query query = getEntityManager().createQuery(jpql.toString());

	}
	
	public Convenio editarConvenio(Integer idUser) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("con ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("Inner Join Paciente pa on p.paciente.id = pa.id ");
		jpql.append("Inner Join Convenio con on pa.convenio.id = con.id ");
		jpql.append("Where p.id = "+idUser);

		Query query = getEntityManager().createQuery(jpql.toString());

		return (Convenio) query.getSingleResult();
	}

	public List<ConvenioF> findConvenioF(Integer id) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("conF ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("Inner Join Paciente pa on p.paciente.id = pa.id ");
		jpql.append("Inner Join Convenio con on pa.convenio.id = con.id ");
		jpql.append("Inner Join ConvenioF conF on con.convenioF.id = conF.id ");
		jpql.append("Where p.id = " + id);

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}
	
	public void excluirCon(Integer idCon) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("Delete ");
		jpql.append("FROM ");
		jpql.append("Convenio con ");
		jpql.append("Where con.id = "+idCon);	
		
		Query query = getEntityManager().createQuery(jpql.toString());
	}
	
	public Convenio findByConvenio(Integer id, Integer id2) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("con ");
		jpql.append("FROM ");
		jpql.append("Pessoa p ");
		jpql.append("Inner Join Paciente pa on p.paciente.id = pa.id ");
		jpql.append("Inner Join Convenio con on pa.convenio.id = con.id ");
		jpql.append("Where p.id = " + id + " and con.id = "+ id2);

		Query query = getEntityManager().createQuery(jpql.toString());

		return (Convenio) query.getSingleResult();
	}

	public List<ConvenioF> findByConvenioF(Integer id, Integer id2) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("conF ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Convenio con on pa.convenio.id = con.id ");
		jpql.append("Inner Join ConvenioF conF on con.convenioF.id = conF.id ");
		jpql.append("Where pe.id = " + id+" and con.id = "+id2);

		Query query = getEntityManager().createQuery(jpql.toString());

		return query.getResultList();
	}
	
	public List<ConvenioF> findConvenioFByNome(Integer idUser, Integer idCon, String nome) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("conF ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Convenio con on pa.convenio.id = con.id ");
		jpql.append("Inner Join ConvenioF conF on con.convenioF.id = conF.id ");
		jpql.append("Where pe.id = " + idUser+" and upper(conF.nome) like upper(:nome)");

		Query query = getEntityManager().createQuery(jpql.toString());
		query.setParameter("nome", "%" + nome + "%");

		return query.getResultList();
	}

	public ConvenioF editarConvenioF(Integer idUser, Integer idCon, Integer idConF) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append("conF ");
		jpql.append("FROM ");
		jpql.append("Pessoa pe ");
		jpql.append("Inner Join Paciente pa on pe.paciente.id = pa.id ");
		jpql.append("Inner Join Convenio con on pa.convenio.id = con.id ");
		jpql.append("Inner Join ConvenioF conF on con.convenioF.id = conF.id ");
		jpql.append("Where pe.id = " + idUser+" and con.id = "+idCon+" and conF.id = "+ idConF);

		Query query = getEntityManager().createQuery(jpql.toString());

		return (ConvenioF) query.getSingleResult();
	}
	
}
