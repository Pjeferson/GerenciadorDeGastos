package com.poo.projeto.model.exceptions;

public class PrecoInvaliadoException extends Exception{
	public PrecoInvaliadoException() {
		super("Escreva um valor v�lido para o pre�o");
	}
}
