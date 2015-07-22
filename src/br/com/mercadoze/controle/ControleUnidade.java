package br.com.mercadoze.controle;

import java.util.List;

import br.com.mercadoze.dao.UnidadeDAO;
import br.com.mercadoze.entidade.Unidade;
import br.com.mercadoze.exception.ErroCadastroException;

public class ControleUnidade {
	public void incluir(Unidade unidade) throws ErroCadastroException{
		(new UnidadeDAO()).incluir(unidade);
	}

	public List buscar(Unidade un) {
		return (new UnidadeDAO()).buscar(un);
	}
}
