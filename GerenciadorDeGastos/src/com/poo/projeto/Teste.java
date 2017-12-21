package com.poo.projeto;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.poo.projeto.model.Gasto;
import com.poo.projeto.model.dao.GastoDAO;

public class Teste {
	public static void main(String[] args) {
		GastoDAO gastodao = new GastoDAO();
		Gasto gasto = new Gasto();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String dataTeste = "01/01/2018";
		Date data = new Date();
		try {
			data = fmt.parse(dataTeste);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gasto.setData(data);
		gasto.setDescricao("Testando");
		gasto.setPreco(1.20);
		//gasto.setId(10);
		/*
		 * try { gastodao.excluirGasto(gasto); } catch (SQLException e1) {
		 * e1.printStackTrace(); }
		 */

		try {
			gastodao.salvarGasto(gasto);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			List<Gasto> gastos = gastodao.listarGastos();
			for (Gasto gasto2 : gastos) {
				System.out.println("ID: " + gasto2.getId());
				System.out.println("Data: " + new SimpleDateFormat("dd/MM/yyyy").format(gasto2.getData()));
				System.out.println("Preço: " + gasto2.getPreco());
				System.out.println("Descrição: " + gasto2.getDescricao());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		gastodao.fecharDAO();

	}
}
