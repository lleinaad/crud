package implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbExcecao;
import entities.Departamento;

public class DepartamentoJDBC {
	
	private Connection conexao;
	
	public DepartamentoJDBC(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void create(Departamento dep) {
		PreparedStatement statement = null;
		try {
			statement = conexao.prepareStatement("INSERT INTO departamento (Nome) VALUES (?)");
			statement.setString(1, dep.getNome());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbExcecao(e.getMessage());
		} 
		finally {
			DB.fecharStatement(statement);
		}
	}
	
	public List<Departamento> findAll() {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = conexao.prepareStatement("SELECT * FROM departamento ORDER BY Nome");
			resultSet = statement.executeQuery();

			List<Departamento> departamentos = new ArrayList<>();

			while (resultSet.next()) {
				Departamento dep = new Departamento();
				dep.setId(resultSet.getInt("Id"));
				dep.setNome(resultSet.getString("Nome"));
				
				departamentos.add(dep);
			}
			return departamentos;
		}
		catch (SQLException e) {
			throw new DbExcecao(e.getMessage());
		}
		finally {
			DB.fecharStatement(statement);
			DB.fecharResultSet(resultSet);
		}
	}
	
	public Departamento findById(Integer id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = conexao.prepareStatement("SELECT * FROM departamento WHERE Id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				Departamento dep = new Departamento();
				dep.setId(resultSet.getInt("Id"));
				dep.setNome(resultSet.getString("Nome"));
				
				return dep;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbExcecao(e.getMessage());
		}
		finally {
			DB.fecharStatement(statement);;
			DB.fecharResultSet(resultSet);
		}
	}
	
	public void update(Departamento dep) {
		PreparedStatement statement = null;
		try {
			statement = conexao.prepareStatement("UPDATE departamento SET Nome = ? WHERE Id = ?");

			statement.setString(1, dep.getNome());
			statement.setInt(2, dep.getId());

			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbExcecao(e.getMessage());
		} 
		finally {
			DB.fecharStatement(statement);
		}
	}
	
	public void deleteById(Integer id) {
		PreparedStatement statement = null;
		try {
			statement = conexao.prepareStatement("DELETE FROM departamento WHERE Id = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbExcecao(e.getMessage());
		} 
		finally {
			DB.fecharStatement(statement);
		}
	}

}
