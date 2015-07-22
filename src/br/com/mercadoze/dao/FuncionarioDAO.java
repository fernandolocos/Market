package br.com.mercadoze.dao;

import br.com.mercadoze.entidade.Funcionario;

public class FuncionarioDAO {
	
	public Funcionario buscaFuncionario(long id){
		
		Funcionario f = new Funcionario();
		f.setNome("Joaquina da Silva");
		f.setId(1);
		f.setMatricula(123);
		
		return f;
		
	}

}
