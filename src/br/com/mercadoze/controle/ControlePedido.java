package br.com.mercadoze.controle;

import java.util.ArrayList;

import br.com.mercadoze.dao.ClienteDAO;
import br.com.mercadoze.dao.PedidoDAO;
import br.com.mercadoze.entidade.Pedido;
import br.com.mercadoze.entidade.Pessoa;
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
	

	public Pessoa buscaCliente(long codigo){
		return (new ClienteDAO()).buscaCliente(codigo);
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
	 * @throws ProdutoNotFoundException, EstoqueInsuficienteException 
	 */
	public void processaSaida(int quantidade, double desconto
			, long codigoProduto) 
		throws ProdutoNotFoundException, EstoqueInsuficienteException {
		
		usaDAO();

		Produto p = dao.buscaProduto(codigoProduto);
		if (p != null){
			if (p.getEstoque() >= quantidade){
				Saida s = new Saida();
				s.setProduto(p);
				s.setQtde(quantidade);
				s.setDesconto(desconto);
				s.setValor(p.getValor());
				
				if (pedido.getSaida() == null){
					pedido.setSaida(new ArrayList<Saida>());
				}
				pedido.getSaida().add(s);
			}else{
				throw new EstoqueInsuficienteException(p);
			}
		}else{
			 throw new ProdutoNotFoundException(codigoProduto);
		
		}
		
	}
	
	private void usaDAO(){
		if (this.dao == null){
			dao = new PedidoDAO();
		}
	}
	public void finalizaPedido() {
		
		dao.persistePedido(this.pedido);
		dao.finalizar();
		
	}
	
	
}
