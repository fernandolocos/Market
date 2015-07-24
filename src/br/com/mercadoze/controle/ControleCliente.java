package br.com.mercadoze.controle;

import br.com.mercadoze.dao.ClienteDAO;
import br.com.mercadoze.entidade.Pessoa;

public class ControleCliente {

	public Pessoa buscaCliente(long codigo) {
		return (new ClienteDAO()).buscaCliente(codigo);
	}
}
