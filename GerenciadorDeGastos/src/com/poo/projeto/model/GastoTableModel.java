package com.poo.projeto.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class GastoTableModel extends AbstractTableModel {

	private List<Gasto> dados;
	private String[] colunas = { "Valor", "Data", "Descrição" };

	public GastoTableModel() {
		super();
		dados = new ArrayList<>();
	}
	
	public void changeGastoTableModel(List<Gasto> gastos) {
		dados = gastos;
		this.fireTableDataChanged();
	}
	
	public int getIdAt(int id) {
		return dados.get(id).getId();
	}
	
	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return dados.get(linha).getPreco();

		case 1:
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			return fmt.format(dados.get(linha).getData());

		case 2:
			return dados.get(linha).getDescricao();
		default:
			return null;
		}
	}

	public void addRow(Gasto g) {
		dados.add(g);
		this.fireTableDataChanged();
	}

	public void removeRow(int linha) {
		dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
		switch (coluna) {
		case 0:
			dados.get(linha).setPreco(Double.parseDouble((String) valor));
			break;
		case 1:
			Date data = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			try {
				data = fmt.parse((String) valor);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dados.get(linha).setData(data);
			break;
		case 2:
			dados.get(linha).setDescricao((String) valor);
			break;
		}
		this.fireTableRowsUpdated(linha, linha);
	}
}
