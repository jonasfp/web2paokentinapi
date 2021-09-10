package com.paokentin.api.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/web2padaria";

	private static final String USUARIO = "root";

	private static final String SENHA = "1234";

	private static Connection conexao = null;

	public static Connection getCurrentConnection() throws SQLException {

		if (conexao == null) {
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		}

		return conexao;

	}

	static Connection getNewConnection() throws SQLException {

		return DriverManager.getConnection(URL, USUARIO, SENHA);

	}

}
