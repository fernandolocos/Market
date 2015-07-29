package br.com.mercadoze.controle;

import java.util.List;

import br.com.mercadoze.dao.ProdutoDAO;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Saida;
import br.com.mercadoze.exception.ErroCadastroException;

public class ControleProduto {

	
	public void incluir(Produto produto) throws ErroCadastroException {
		(new ProdutoDAO()).incluir(produto);
	}
	
	public List<Produto> produtoBaixoEstoque(){
		
		return (new ProdutoDAO()).produtoBaixoEstoque();
	}
	
}
