package br.com.mercadoze.controle;

import java.util.ArrayList;

import br.com.mercadoze.dao.PedidoDAO;
import br.com.mercadoze.entidade.Pedido;
import br.com.mercadoze.entidade.Produto;
import br.com.mercadoze.entidade.Saida;
import br.com.mercadoze.exception.EstoqueInsuficienteException;
import br.com.mercadoze.exception.ProdutoNotFoundException;

public class ControlePedido {
	
	private Pedido pedido;
	private PedidoDAO dao;
	
	public ControlePedido(){
		this.pedido = new Pedido();
	}
	public ControlePedido(Pedido pedido){
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	/**
	 * 
	 * @param quantidade
	 * @param desconto
	 * @param codigoProduto
	 * @throws ProdutoNotFoundException
	 * @throws EstoqueInsuficienteException
	 */
	public void processaSaida(int quantidade, double desconto, long codigoProduto) 
			throws ProdutoNotFoundException, EstoqueInsuficienteException {
		
		usaDAO(); // recupera a conexão já aberta
		
		Produto p = dao.buscaProduto(codigoProduto);
		if(p != null){
			if(p.getEstoque() >= quantidade){
				Saida saida = new Saida();
				saida.setQtde(quantidade);
				saida.setDesconto(desconto);
				saida.setProduto(p);
				saida.setValor(p.getValor());
				
				if(pedido.getSaida() == null){
					pedido.setSaida(new ArrayList<Saida>());
				}
				pedido.getSaida().add(saida); // referência cruzada - Pedido tem list saída
											  // e Saida tem atributo pedido para facilitar busca
			} else {
				throw new EstoqueInsuficienteException(p);
			}
		} else {
			throw new ProdutoNotFoundException(codigoProduto);
		}
	}
	
	private void usaDAO(){
		if(this.dao == null){
			this.dao = new PedidoDAO();
		}
	}
	
	public void finalizaPedido() {

		dao.persistePedido(this.pedido);
		dao.finalizar();
		
	}
	
}
