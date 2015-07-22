package br.com.mercadoze.exception;

import br.com.mercadoze.entidade.Produto;

public class EstoqueInsuficienteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	
	public EstoqueInsuficienteException(Produto produto){
		super("Estoque insuficiente!");
		this.produto = produto;
	}
	public EstoqueInsuficienteException(Produto produto, Throwable e){
		super("Estoque insuficiente!", e);
		this.produto = produto;
	}
	
	public Produto getCodigoProduto(){
		return this.produto;
	}
}
