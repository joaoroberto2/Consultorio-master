package com.consultorio.model;

public enum UF {
	AC(12, "Acre", "AC"), AL(27, "Alagoas", "AL"), AP(16, "Amap�", "AP"),
	AM(13, "Amazonas", "AM"), BA(29, "Bahia", "BA"), CE(23, "Cear�", "CE"),
	DF(53, "Distrito Federal", "DF"), ES(32, "Esp�rito Santo", "Es"), GO(52, "Goi�s", "GO"),
	MA(21, "Maranh�o", "MA"), MT(51, "Mato Grosso", "MT"), MS(50, "Mato Grosso do Sul", "MS"),
	MG(31, "Minas Gerais", "MG"), PA(15, "Par�", "PA"), PB(25, "Para�ba", "PB"),	
	PR(41, "Paran�", "PR"), PE(26, "Pernambuco", "PE"), PI(22, "Piau�", "PI"),
	RJ(33, "Rio de Janeiro", "RJ"), RN(24, "Rio Grande do Norte", "RN"), RS(43, "Rio Grande do Sul", "RS"),
	RO(11, "Rond�nia", "RO"), RR(14, "Roraima", "RR"), SC(42, "Santa Catarina", "SC"),	
	SP(35, "S�o Paulo", "SP"), SE(28, "Sergipe", "SE"), TO(17, "Tocantins", "TO");
	
	Integer codigo;
	
	String estado;

	String sigla;
	

	private UF(Integer codigo, String estado, String sigla) {
		this.codigo = codigo;
		this.estado = estado;
		this.sigla = sigla;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public String getEstado() {
		return estado;
	}

}
