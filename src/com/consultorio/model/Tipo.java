package com.consultorio.model;

public enum Tipo {
	
	INDUSTRIALIZADOS("Industrializados"),
	MANIPULADOS("Manipulados"),
	COMPOSICAO("Composi��o");

	
	String descricao;

	private Tipo(String descricao) {
		this.descricao = descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
