package com.poo.projeto.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.poo.projeto.model.Gasto;
import com.poo.projeto.model.dao.GastoDAO;
import com.poo.projeto.model.exceptions.DataInvaliadaException;
import com.poo.projeto.model.exceptions.PrecoInvaliadoException;
import com.poo.projeto.model.util.GastoUtil;

public class GerenciadorController {
	private List<Gasto> gastos;
	private GastoDAO gastoDAO;

	public GerenciadorController() throws SQLException {
		gastoDAO = new GastoDAO();
		this.gastos = gastoDAO.listarGastos();
	}

	public void filtrarGastos() throws SQLException {
		gastos = gastoDAO.listarGastos();
	}

	public void filtrarGastos(Date data1, Date data2) throws SQLException, DataInvaliadaException {
		if (data1 == null || data2 == null) {
			throw new DataInvaliadaException();
		} else {
			gastos = gastoDAO.listarGastos(data1, data2);
		}
	}

	public void addGasto(String preco, Date data, String desc)
			throws SQLException, DataInvaliadaException, PrecoInvaliadoException {
		Gasto gasto = new Gasto();
		if (data != null) {
			gasto.setData(data);
		} else {
			throw new DataInvaliadaException();
		}

		if (GastoUtil.validaPreco(preco)) {
			gasto.setPreco(Double.parseDouble(preco));
		} else {
			throw new PrecoInvaliadoException();
		}

		gasto.setDescricao(desc);
		gastoDAO.salvarGasto(gasto);

		gastos = gastoDAO.listarGastos();
	}

	public void removerGasto(int id) throws SQLException {
		Gasto gasto = new Gasto();
		gasto.setId(id);
		gastoDAO.excluirGasto(gasto);

		gastos = gastoDAO.listarGastos();
	}

	public double getValorTotal() {
		double total = 0;
		for (Gasto gasto : gastos) {
			total += gasto.getPreco();
		}
		return total;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
}
