package br.com.mercadoze.controle;

import br.com.mercadoze.dao.FuncionarioDAO;
import br.com.mercadoze.entidade.Funcionario;

public class ControleFuncionario {
		
	public Funcionario buscaFuncionario(long id){
		return (new FuncionarioDAO()).buscaFuncionario(id);
	}
	
}
