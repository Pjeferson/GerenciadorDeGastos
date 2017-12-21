package com.poo.projeto.model.util;

public class GastoUtil {
	public static boolean validaPreco (String preco) {
		try {
	     Double.parseDouble(preco);
	     return true;
		}catch (NumberFormatException e) {
			return false;
		}
	 }
}
