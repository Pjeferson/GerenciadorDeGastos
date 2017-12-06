package com.poo.projeto.model;

import java.util.ArrayList;

public class Usuario {
	private String nome;
	private String senha;
	private ArrayList<Gasto> gastos;
	
	public Usuario () {
		gastos = new ArrayList<>();
	}
	
	public Usuario (ArrayList<Gasto> gastos) {
		this.gastos = gastos;
	}
	
	public void addGasto(Gasto gasto) {
		gastos.add(gasto);
	}
	
	public void removerGasto(Gasto gasto) {
		gastos.remove(gasto);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
