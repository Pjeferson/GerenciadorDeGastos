package com.poo.projeto.model;

import java.util.Date;

public class Gasto {
	private int id;
	private Date data;
	private double preco;
	private String descricao;
	
	public Gasto() {
		
	}
	public Gasto(int id, Date data, double preco, String descricao) {
		this.id = id;
		this.data = data;
		this.preco = preco;
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
