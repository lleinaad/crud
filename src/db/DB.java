package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conexao = null;
	
	public static Connection getConexao() {
		if (conexao == null) {
			try {
				Properties propriedades = carregarPropriedades();
				String url = propriedades.getProperty("dburl");
				conexao = DriverManager.getConnection(url, propriedades);
			}
			catch (SQLException e) {
				throw new DbExcecao(e.getMessage());
			}
		}
		return conexao;
	}
	
	public static void fecharConexao() {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new DbExcecao(e.getMessage());
			}
		}
	}
	
	private static Properties carregarPropriedades() {
		try (FileInputStream arquivo = new FileInputStream("db.properties")) {
			Properties propriedades = new Properties();
			propriedades.load(arquivo);
			return propriedades;
		}
		catch (IOException e) {
			throw new DbExcecao(e.getMessage());
		}
	}
	
	public static void fecharStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbExcecao(e.getMessage());
			}
		}
	}

	public static void fecharResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbExcecao(e.getMessage());
			}
		}
	}
}
