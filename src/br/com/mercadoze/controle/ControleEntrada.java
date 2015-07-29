package br.com.mercadoze.controle;

import br.com.mercadoze.dao.EntradaDAO;
import br.com.mercadoze.dao.PedidoDAO;
import br.com.mercadoze.entidade.Entrada;
import br.com.mercadoze.entidade.Pessoa;
import br.com.mercadoze.entidade.Produto;

public class ControleEntrada {

	public void incluir(Entrada entrada) throws Exception{
		(new EntradaDAO()).incluir(entrada);
	}

	public void processaEntrada(Entrada entrada, long produtoId, int qtde) throws Exception {
		
		Produto p = (new PedidoDAO()).buscaProduto(produtoId);
		entrada.setQtde(qtde);
		entrada.setProduto(p);
		
		incluir(entrada);
		
	}
	
	public Pessoa buscaFornecedor(long codForn){
		
		Pessoa p = (new EntradaDAO()).buscaFornecedor(codForn);
		
		return p;
		
	}
}
