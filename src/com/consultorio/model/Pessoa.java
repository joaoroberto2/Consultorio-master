package com.consultorio.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.consultorio.validation.PessoaValidation;
import com.consultorio.validation.Validation;

@Entity
@NamedQuery(name = "logar", query = "SELECT p FROM Pessoa p WHERE p.email = :email and p.senha = :senha")
public class Pessoa extends DefaultEntity<Pessoa>{
	
	private static final long serialVersionUID = -6432207614516019961L;
	
	private String nome;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String senha;
	private Date dataNascimento;
	private String rg;
	private String cpf;
	private String naturalidade;
	private String emissor;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idendereco")
	private Endereco endereco;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpessoa")
	private List<Telefone> telefone;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idmedico", unique = true)
	private Medico medico;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpaciente", unique = true)
	private Paciente paciente;
	
	@JoinColumn(name = "idadministrador", unique = true)
	private Administrador adm;

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	@PrePersist
	private void atualizarDadosAntesInsert() {
		this.dataCadastro = new Date();
		this.dataAlteracao = this.dataCadastro;
	}
	
	@PreUpdate
	private void atualizarDadosAntesUpdate() {
		this.dataAlteracao = new Date();
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	@Override
	public Validation<Pessoa> getValidation() {
		return new PessoaValidation();
	}
	
}
