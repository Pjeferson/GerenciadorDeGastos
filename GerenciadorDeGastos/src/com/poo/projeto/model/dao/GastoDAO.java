package com.poo.projeto.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.poo.projeto.model.Gasto;

public class GastoDAO {
	private Connection con;

	public GastoDAO() {
		try {
			con = Conexao.getConexao();
			String sql = "CREATE TABLE IF NOT EXISTS gastos (id integer PRIMARY KEY AUTOINCREMENT NOT NULL, data Date, preco integer, descricao VARCHAR(255));";
			Statement stm = con.createStatement();
			stm.executeUpdate(sql);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvarGasto(Gasto gasto) throws SQLException {
		String valor = String.format("%.0f", gasto.getPreco() * 100);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "insert into gastos (data, preco, descricao) values (?, ?, ?);";
		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, df.format(gasto.getData()));
		stmt.setInt(2, Integer.parseInt(valor));
		stmt.setString(3, gasto.getDescricao());
		stmt.execute();
		/*
		if (stmt.execute()) {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				gasto.setId(rs.getInt("id"));
			}
		}
		*/
		stmt.close();
	}

	public void excluirGasto(Gasto gasto) throws SQLException {
		String sql = "DELETE FROM gastos WHERE id = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, gasto.getId());
		stmt.executeUpdate();

	};

	public List<Gasto> listarGastos() throws SQLException {
		List<Gasto> gastos = new ArrayList<Gasto>();
		// construir array list a parir do resultset
		Statement stat = con.createStatement();
		ResultSet rs = stat.executeQuery("select * from gastos order by data;");
		while (rs.next()) {
			int id = rs.getInt("id");

			String dataIn = rs.getString("data");
			Date data = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				data = formato.parse(dataIn);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			int precoIn = rs.getInt("preco");
			double preco = (double) precoIn / 100;

			String descricao = rs.getString("descricao");
			Gasto gasto = new Gasto(id, data, preco, descricao);
			gastos.add(gasto);
		}
		rs.close();
		stat.close();
		return gastos;
	}
	public List<Gasto> listarGastos(Date data1, Date data2) throws SQLException {
		List<Gasto> gastos = new ArrayList<Gasto>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sql = "select * from gastos where ? <= data and data <= ? order by data;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, formato.format(data1));
		stmt.setString(2, formato.format(data2));
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");

			String dataIn = rs.getString("data");
			Date data = new Date();
			
			try {
				data = formato.parse(dataIn);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			int precoIn = rs.getInt("preco");
			double preco = (double) precoIn / 100;

			String descricao = rs.getString("descricao");
			Gasto gasto = new Gasto(id, data, preco, descricao);
			gastos.add(gasto);
		}
		rs.close();
		stmt.close();
		return gastos;
	}

	public void fecharDAO() {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
