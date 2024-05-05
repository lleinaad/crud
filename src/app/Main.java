package app;

import java.util.List;

import db.DB;
import entities.Departamento;
import implementacao.DepartamentoJDBC;

public class Main {

	public static void main(String[] args) {
		DepartamentoJDBC departamentoJDBC = new DepartamentoJDBC(DB.getConexao());
		
		System.out.println("create");
		Departamento departamento = new Departamento(null, "Computadores");
		departamentoJDBC.create(departamento);
		
		System.out.println("\nfindAll");
		List<Departamento> departamentos = departamentoJDBC.findAll();
		for (Departamento dep : departamentos) {
			System.out.println(dep);
		}
		
		System.out.println("\nfindById");
		Departamento dep = departamentoJDBC.findById(1);
		System.out.println(dep);
		
		System.out.println("\nupdate");
		Departamento novoDepartamento = departamentoJDBC.findById(1);
		novoDepartamento.setNome("Eletronicos");
		departamentoJDBC.update(novoDepartamento);
		
		System.out.println("\ndelete");
		departamentoJDBC.deleteById(1);
	}
}
