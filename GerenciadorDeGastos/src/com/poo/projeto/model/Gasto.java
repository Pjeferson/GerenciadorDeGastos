package com.poo.projeto.model;

public class Gasto {
	private String data;
	private String preco;
	private String descricao;
	
	public Gasto(String data, String preco, String descricao) {
		this.data = data;
		this.preco = preco;
		this.descricao = descricao;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
