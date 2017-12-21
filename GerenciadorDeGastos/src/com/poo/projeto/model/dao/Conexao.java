package com.poo.projeto.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConexao() throws SQLException {
		Connection conn;
		String url = "jdbc:sqlite:gastos.db";
		conn = DriverManager.getConnection(url);
		return conn;
	}
}
