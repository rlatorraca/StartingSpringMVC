package com.rlsp.faturaSpring.model;

public enum StatusTitulo {

	PENDENTE("Pendente"),
	RECEBIDO("Recebido"),
	RECEBIDOCOMULTA("Recebido com Multa");
	
	private String descricao;
	
	StatusTitulo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}